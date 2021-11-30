package cn.gov.zcy.ares.rest.annotation;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-11-30 11:29:30
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Conditional({MissingInstanceCondition.class})
public @interface ConditionalOnInterfaceMissingInstance {
    Class<?> baseClass() default void.class;
}
