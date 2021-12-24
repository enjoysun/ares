package cn.gov.zcy.ares.adrasteia.config;

import cn.gov.zcy.ares.adrasteia.annotation.LogRecord;
import cn.gov.zcy.ares.adrasteia.core.chain.*;
import cn.gov.zcy.ares.adrasteia.core.context.AresLogContext;
import cn.gov.zcy.ares.adrasteia.core.envluation.AresEvaluationContext;
import cn.gov.zcy.ares.adrasteia.core.envluation.AresExpressionEvaluator;
import cn.gov.zcy.ares.adrasteia.core.envluation.AresValueParser;
import cn.gov.zcy.ares.adrasteia.meta.LogPersistContext;
import cn.gov.zcy.ares.adrasteia.meta.MethodInvokeResult;
import cn.gov.zcy.ares.adrasteia.spi.operator.OperatorInfuseService;
import cn.gov.zcy.ares.adrasteia.spi.persistent.LogPersistService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-12-08 18:37:05
 */
@Slf4j
@Aspect
@Component
public class LogInterludeAspect {


    @Autowired
    private AresValueParser aresValueParser;

    @Autowired
    private OperatorInfuseService operatorInfuseService;

    @Autowired
    private LogPersistService logPersistService;

    @Autowired
    private AresExpressionEvaluator aresExpressionEvaluator;

    @Autowired
    private List<LogRecordPrepareFilter> filters;

    @Around("@annotation(cn.gov.zcy.ares.adrasteia.annotation.LogRecord)")
    public Object logExecuteInterlude(ProceedingJoinPoint joinPoint) {
        Object result = null;
        /*日志前置解析*/
        MethodInvokeResult methodInvokeResult = new MethodInvokeResult();
        LogPersistContext logPersistContext = new LogPersistContext();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        LogRecord annotation = signature.getMethod().getAnnotation(LogRecord.class);
        logPersistContext.setActionNode(annotation.actionNode());
        logPersistContext.setActionClassName(signature.toString());
        logPersistContext.setChildBizId(annotation.childBizId());
        logPersistContext.setCondition(annotation.condition());
        logPersistContext.setIdentityType(annotation.identityType());
        String traceId = "";
        if (StringUtils.isBlank(annotation.traceId())) {
            if (!StringUtils.isBlank(MDC.get("trace-id"))) {
                traceId = MDC.get("trace-id");
            }
        } else {
            traceId = annotation.traceId();
        }
        logPersistContext.setTraceId(traceId);
        logPersistContext.setActionType(annotation.actionType());
        logPersistContext.setContext(annotation.context());
        /*函数执行*/
        try {
            result = joinPoint.proceed();
            methodInvokeResult.setInvokeSuccess(true);
        } catch (Throwable throwable) {
            methodInvokeResult.setInvokeSuccess(false);
            methodInvokeResult.setThrowable(throwable);
            methodInvokeResult.setThrowableMessage(throwable.getMessage());
        }

        /*日志生产流程-不影响业务函数执行*/
        AresEvaluationContext evaluationContext = aresExpressionEvaluator.createEvaluationContext(result, joinPoint.getTarget().getClass(), signature.getMethod(), result, joinPoint.getArgs());
        try {
            PersistLocal persistLocal = new PersistLocal(logPersistService, annotation.persistBefore());
            OperatorInjection.getInstance().doInjection(operatorInfuseService, logPersistContext, evaluationContext);
            FilterChain filterChain = new FilterChain(persistLocal);
//                filterChain.addFilter(
//                        new FillOperatorFilter(operatorInfuseService)
//                );
            filterChain.addFilter(filters);
            filterChain.doFilter(annotation, evaluationContext, logPersistContext, filterChain);
            if (null != methodInvokeResult.getThrowable()) {
                throw methodInvokeResult.getThrowable();
            }
        } catch (Throwable throwable) {
            log.error(String.format("%s日志记录错误，错误原因:%s", logPersistContext.getActionClassName(), throwable.getMessage()));
        } finally {
            logPersistContext = null;
            AresLogContext.clear();
        }
        return result;
    }
}
