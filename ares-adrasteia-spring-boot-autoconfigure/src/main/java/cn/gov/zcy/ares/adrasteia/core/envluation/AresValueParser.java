package cn.gov.zcy.ares.adrasteia.core.envluation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-12-09 15:39:27
 */
public class AresValueParser {

    @Autowired
    private AresExpressionEvaluator aspectExpressionEvaluator;

    public <T> T valueParser(ExpressionEnum expressionEnum, JoinPoint joinPoint, Object result, String key, Class<T> tClass) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return aspectExpressionEvaluator.getValue(expressionEnum, result, joinPoint.getTarget().getClass(), signature.getMethod(), key, tClass, result, joinPoint.getArgs());
    }
}
