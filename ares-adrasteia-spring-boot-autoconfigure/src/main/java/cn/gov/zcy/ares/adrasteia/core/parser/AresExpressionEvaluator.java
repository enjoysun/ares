package cn.gov.zcy.ares.adrasteia.core.parser;

import cn.gov.zcy.ares.adrasteia.core.envluation.AresEvaluationContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.support.AopUtils;
import org.springframework.context.expression.AnnotatedElementKey;
import org.springframework.context.expression.CachedExpressionEvaluator;
import org.springframework.context.expression.MethodBasedEvaluationContext;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.lang.Nullable;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-12-09 09:56:44
 */
public class AresExpressionEvaluator extends CachedExpressionEvaluator {

    /**
     * 参数名发现api
     */
    private final ParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();

    private static final ParserContext PARSER_CONTEXT = new AresParserPlaceholder();


    /**
     * 作用方法表达式元素缓存
     */
    private final Map<ExpressionKey, Expression> expressionCache = new ConcurrentHashMap<>(64);

    private final Map<ExpressionKey, Expression> conditionCache = new ConcurrentHashMap<>(64);


    /**
     * 作用方法参数和结果缓存
     */
    private final Map<AnnotatedElementKey, Method> targetMethodCache = new ConcurrentHashMap<>(64);


    private Method getTargetMethod(Class<?> targetClass, Method method) {
        AnnotatedElementKey annotatedElementKey = new AnnotatedElementKey(method, targetClass);
        Method targetMethod = this.targetMethodCache.get(annotatedElementKey);
        if (null == targetMethod) {
            // 代理处理工具<可以辨别属于cglib还是接口等原信息获取，getMostSpecificMethod即获取类被代理的原方法>
            targetMethod = AopUtils.getMostSpecificMethod(method, targetClass);
            if (null == targetMethod) {
                targetMethod = method;
            }
            this.targetMethodCache.put(annotatedElementKey, targetMethod);
        }
        return targetMethod;
    }

    public EvaluationContext createEvaluationContext(Object cacheRootObject, Class<?> targetClass, Method method, Object result, Object... args) {
        Method originalTargetMethod = getTargetMethod(targetClass, method);
        // 扩展日志上下文<增强MethodBasedEvaluationContext，在增强类中实现>
        return new AresEvaluationContext(cacheRootObject, originalTargetMethod, args, parameterNameDiscoverer, result, null);
    }

    public <T> T parserExpression(ExpressionEnum expressionEnum, String expressionSpel, AnnotatedElementKey annotatedElementKey, EvaluationContext evaluationContext, Class<T> tClass) {
        Expression expression;
        if (expressionEnum == ExpressionEnum.CONDITION) {
            expression = getExpression(this.conditionCache, annotatedElementKey, expressionSpel);
        } else if (expressionEnum == ExpressionEnum.TEXT) {
            expression = getExpression(this.expressionCache, annotatedElementKey, expressionSpel);
        } else {
            return null;
        }
        return expression.getValue(evaluationContext, tClass);
    }

    public <T> T getValue(ExpressionEnum expressionEnum, @Nullable Object rootObject, Class<?> source, Method method, String expression, Class<T> target, Object result, Object... args) {
        if (null == args) {
            return null;
        }
        EvaluationContext evaluationContext = createEvaluationContext(rootObject, source, method, result, args);
        AnnotatedElementKey annotatedElementKey = new AnnotatedElementKey(method, source);
        return parserExpression(expressionEnum, expression, annotatedElementKey, evaluationContext, target);
    }

    @Override
    protected Expression getExpression(Map<ExpressionKey, Expression> cache, AnnotatedElementKey elementKey, String expression) {
        ExpressionKey aresExpressionKey = new AresExpressionKey(elementKey, expression);
        Expression expr = cache.get(aresExpressionKey);
        if (expr == null) {
            expr = this.getParser().parseExpression(expression, PARSER_CONTEXT);
            cache.put(aresExpressionKey, expr);
        }

        return expr;
    }

    private static class AresExpressionKey extends CachedExpressionEvaluator.ExpressionKey {

        protected AresExpressionKey(AnnotatedElementKey element, String expression) {
            super(element, expression);
        }
    }
}
