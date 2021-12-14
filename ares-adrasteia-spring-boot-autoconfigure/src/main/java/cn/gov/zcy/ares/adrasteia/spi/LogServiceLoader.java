package cn.gov.zcy.ares.adrasteia.spi;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-12-14 17:45:01
 */
public class LogServiceLoader {
    public static <T> void serviceFind(Class<T> clazz, DefaultListableBeanFactory beanFactory) {
        ServiceLoader<T> load = ServiceLoader.load(clazz);
        Iterator<T> iterator = load.iterator();
        while (iterator.hasNext()) {
            T service = iterator.next();
            String serviceName = StringUtils.uncapitalize(service.getClass().getSimpleName());
            BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(service.getClass());
            GenericBeanDefinition genericBeanDefinition = (GenericBeanDefinition) beanDefinitionBuilder.getRawBeanDefinition();
            genericBeanDefinition.setAutowireMode(GenericBeanDefinition.AUTOWIRE_BY_TYPE);
            beanFactory.registerBeanDefinition(serviceName, genericBeanDefinition);
        }
    }
}
