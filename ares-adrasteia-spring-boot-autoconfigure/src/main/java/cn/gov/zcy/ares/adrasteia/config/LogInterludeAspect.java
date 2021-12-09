package cn.gov.zcy.ares.adrasteia.config;

import cn.gov.zcy.ares.adrasteia.annotation.LogRecord;
import cn.gov.zcy.ares.adrasteia.core.context.AresLogContext;
import cn.gov.zcy.ares.adrasteia.core.envluation.AresEvaluationContext;
import cn.gov.zcy.ares.adrasteia.core.parser.AresExpressionEvaluator;
import cn.gov.zcy.ares.adrasteia.core.parser.AresValueParser;
import cn.gov.zcy.ares.adrasteia.core.parser.ExpressionEnum;
import cn.gov.zcy.ares.adrasteia.meta.LogPersistentContext;
import cn.gov.zcy.ares.adrasteia.meta.MethodInvokeResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-12-08 18:37:05
 */
@Slf4j
@Aspect
@Component
public class LogInterludeAspect {

    private AresExpressionEvaluator expressionEvaluator = new AresExpressionEvaluator();

//    @Autowired
//    private OperatorInfuseService operatorInfuseService;

    @Around("@annotation(cn.gov.zcy.ares.adrasteia.annotation.LogRecord)")
    public Object logExecuteInterlude(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;
        /*日志前置解析*/
        MethodInvokeResult methodInvokeResult = new MethodInvokeResult();
        LogPersistentContext logPersistentContext = new LogPersistentContext();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        LogRecord annotation = signature.getMethod().getAnnotation(LogRecord.class);
        logPersistentContext.setBizId(annotation.bixNo());
        logPersistentContext.setActionNode(annotation.actionNode());
        logPersistentContext.setActionClassName(signature.toString());
        logPersistentContext.setChildBizId(annotation.childBizId());
        logPersistentContext.setCondition(annotation.condition());
        logPersistentContext.setIdentityType(annotation.identityType());
        String traceId = "";
        if (StringUtils.isBlank(annotation.traceId())) {
            if (!StringUtils.isBlank(MDC.get("trace-id"))) {
                traceId = MDC.get("trace-id");
            }
        } else {
            traceId = annotation.traceId();
        }
        logPersistentContext.setTraceId(traceId);
        logPersistentContext.setType(annotation.type());
        logPersistentContext.setStashContext(annotation.stashContext());
        /*函数执行*/
        try {
            result = joinPoint.proceed();
            methodInvokeResult.setInvokeSuccess(true);
        } catch (Throwable throwable) {
            methodInvokeResult.setInvokeSuccess(false);
            methodInvokeResult.setThrowableMessage(throwable.getMessage());
        }

        /*日志生产流程-不影响业务函数执行*/
        try {
            Boolean conditionPass = isConditionPass(joinPoint, annotation.condition());
            logPersistentContext.setConditionPass(conditionPass);
            if (conditionPass) {
                getTargetText(joinPoint, methodInvokeResult.isInvokeSuccess() ? annotation.success() : annotation.fail(), logPersistentContext, result);
            }
            if (null != methodInvokeResult.getThrowable()) {
                throw methodInvokeResult.getThrowable();
            }
        } catch (Throwable throwable) {
            log.error(String.format("%s日志记录错误，错误原因:%s", logPersistentContext.getActionClassName(), throwable.getMessage()));
        } finally {
            AresLogContext.clear();
        }
        return result;
    }

    private Boolean isConditionPass(JoinPoint joinPoint, String condition) {
        try {
            if (StringUtils.isBlank(condition)) {
                return false;
            }
            return AresValueParser.valueParser(ExpressionEnum.CONDITION, joinPoint, null, condition, Boolean.class);
        } catch (Throwable throwable) {
            log.info(String.format("日志提取操作:执行条件执行失败，失败信息:%s", throwable.getMessage()));
            return false;
        }
    }

    /**
     * 模板解析
     */
    private void getTargetText(JoinPoint joinPoint, String template, LogPersistentContext persistentContext, Object result) {
        try {
            if (StringUtils.isBlank(template)) {
                persistentContext.setContent("");
            }
            String content = AresValueParser.valueParser(ExpressionEnum.TEXT, joinPoint, result, template, String.class);
            persistentContext.setContent(content);
        } catch (Throwable throwable) {
            String format = String.format("日志提取操作:日志模板填充执行失败，失败信息:%s", throwable.getMessage());
            log.info(format);
            persistentContext.setErrorMsg(format);
        }
    }
}
