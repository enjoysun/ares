package cn.gov.zcy.ares.adrasteia.config;

import cn.gov.zcy.ares.adrasteia.core.chain.LogRecordPrepareFilter;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AssignableTypeFilter;

import java.util.Objects;
import java.util.Set;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-12-24 18:00:46
 */
public class DefaultFilterImport implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry, BeanNameGenerator importBeanNameGenerator) {
        ClassPathScanningCandidateComponentProvider componentProvider = new ClassPathScanningCandidateComponentProvider(false);
        componentProvider.addIncludeFilter(new AssignableTypeFilter(LogRecordPrepareFilter.class));
        String authenticationPath = "cn.gov.zcy.ares.adrasteia.core.chain.filter";
        Set<BeanDefinition> beanDefinitionSet = componentProvider.findCandidateComponents(authenticationPath);
        beanDefinitionSet.forEach(item-> registry.registerBeanDefinition(Objects.requireNonNull(item.getBeanClassName()), item));
    }
}
