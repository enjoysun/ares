package cn.gov.zcy.ares.rest.register;

import cn.gov.zcy.ares.rest.annotation.EnableRestIntercept;
import cn.gov.zcy.ares.rest.filter.RestRequestInterceptor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AssignableTypeFilter;

import java.util.Objects;
import java.util.Set;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-11-30 11:07:21
 */
public class RestInterceptRegister implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry, BeanNameGenerator importBeanNameGenerator) {
        AnnotationAttributes annotationAttributes = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(EnableRestIntercept.class.getName()));
        ClassPathScanningCandidateComponentProvider classPathScanningCandidateComponentProvider = new ClassPathScanningCandidateComponentProvider(false);
        classPathScanningCandidateComponentProvider.addIncludeFilter(new AssignableTypeFilter(RestRequestInterceptor.class));
        String[] scanInterceptProcessPackage = annotationAttributes.getStringArray("basePackages");
        for(String packages: scanInterceptProcessPackage){
            Set<BeanDefinition> candidateComponents = classPathScanningCandidateComponentProvider.findCandidateComponents(packages);
            candidateComponents.forEach(component -> registry.registerBeanDefinition(Objects.requireNonNull(component.getBeanClassName()), component));
            candidateComponents = null;
        }
    }
}
