package cn.gov.zcy.ares.adrasteia.core.envluation;

import cn.gov.zcy.ares.adrasteia.core.context.AresLogContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.context.expression.MethodBasedEvaluationContext;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.lang.Nullable;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-12-09 15:19:29
 */
public class AresEvaluationContext extends MethodBasedEvaluationContext {


    public AresEvaluationContext(Object rootObject, Method method, Object[] arguments, ParameterNameDiscoverer parameterNameDiscoverer, Object result, @Nullable String errorMsg, @Nullable ApplicationContext beanFactory) {
        super(rootObject, method, arguments, parameterNameDiscoverer);
        /*上下文变量放入rootObject*/
        Map<String, Object> variables = AresLogContext.getVariables();
        if (null != variables) {
            setVariables(variables);
        }
        setVariable("_resource", result);
        setVariable("_errorMsg", errorMsg);
        if (null != beanFactory) {
            setBeanResolver(new BeanFactoryResolver(beanFactory));
        }
        this.method = method;
    }

    public Method getMethod() {
        return method;
    }

    public Class<?> getSource() {
        return source;
    }

    private Method method;

    public void setSource(Class<?> source) {
        this.source = source;
    }

    private Class<?> source;
}
