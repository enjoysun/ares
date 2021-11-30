package cn.gov.zcy.ares.rest.annotation;

import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Objects;


/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-11-30 11:30:37
 */
public class MissingInstanceCondition extends SpringBootCondition {

    @Override
    public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
        ConditionOutcome conditionOutcome = ConditionOutcome.match();
        AnnotationAttributes annotationAttributes = AnnotationAttributes.fromMap(metadata.getAnnotationAttributes(ConditionalOnInterfaceMissingInstance.class.getName()));
        if (null == annotationAttributes) {
            ConditionOutcome.noMatch(String.format("%s no value", ConditionalOnInterfaceMissingInstance.class.getName()));
        }
        Class<?> baseClass = annotationAttributes.getClass("baseClass");
        String[] beanNamesForType = context.getBeanFactory().getBeanNamesForType(baseClass);
        if (beanNamesForType.length > 0) {
            return ConditionOutcome.noMatch(String.format("%s has already living", baseClass.getName()));
        }
        return conditionOutcome;

    }
}
