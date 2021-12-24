package cn.gov.zcy.ares.adrasteia.core.envluation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.expression.AnnotatedElementKey;

import java.lang.reflect.Method;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-12-09 15:39:27
 */
public class AresValueParser {

    @Autowired
    private AresExpressionEvaluator aspectExpressionEvaluator;

    public <T> T valueParser(ExpressionEnum expressionEnum, AresEvaluationContext evaluationContext, AnnotatedElementKey annotatedElementKey, String key, Class<T> tClass) {
        return aspectExpressionEvaluator.getValue(expressionEnum, evaluationContext, annotatedElementKey, key, tClass);
    }
}
