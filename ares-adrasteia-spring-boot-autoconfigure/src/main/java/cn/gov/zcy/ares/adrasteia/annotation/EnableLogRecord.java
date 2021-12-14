package cn.gov.zcy.ares.adrasteia.annotation;

import cn.gov.zcy.ares.adrasteia.config.AspectEvaluationSourceSelector;
import cn.gov.zcy.ares.adrasteia.config.LogInterludeAspect;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-12-14 17:43:13
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@RegisterSpi
@Import({LogInterludeAspect.class, AspectEvaluationSourceSelector.class})
public @interface EnableLogRecord {
}
