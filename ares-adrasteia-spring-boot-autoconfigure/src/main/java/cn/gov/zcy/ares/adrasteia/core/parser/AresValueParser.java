package cn.gov.zcy.ares.adrasteia.core.parser;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-12-09 15:39:27
 */
public class AresValueParser {
    private static class ExpressEvaluator {
        private final static AresExpressionEvaluator EXPRESSION_EVALUATOR;

        static {
            EXPRESSION_EVALUATOR = new AresExpressionEvaluator();
        }

        public static AresExpressionEvaluator getExpressionEvaluator() {
            return EXPRESSION_EVALUATOR;
        }
    }

    public static <T> T valueParser(ExpressionEnum expressionEnum, JoinPoint joinPoint, Object result, String key, Class<T> tClass) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return ExpressEvaluator.getExpressionEvaluator().getValue(expressionEnum, result, joinPoint.getTarget().getClass(), signature.getMethod(), key, tClass, result, joinPoint.getArgs());
    }
}
