package cn.gov.zcy.ares.adrasteia.config;

import cn.gov.zcy.ares.adrasteia.annotation.RegisterSpi;
import cn.gov.zcy.ares.adrasteia.spi.LogServiceLoader;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-12-14 17:44:19
 */
public class SpiBeanRegister implements ImportBeanDefinitionRegistrar, BeanFactoryAware {
    private DefaultListableBeanFactory beanFactory;

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        AnnotationAttributes attributes = AnnotationAttributes.fromMap(annotationMetadata.getAnnotationAttributes(RegisterSpi.class.getName()));
        Class<?>[] registers = attributes.getClassArray("register");
        for (Class clazz : registers) {
            LogServiceLoader.serviceFind(clazz, beanFactory);
        }
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }
}
