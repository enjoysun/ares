package cn.gov.zcy.ares.adrasteia.annotation;

import cn.gov.zcy.ares.adrasteia.config.SpiBeanRegister;
import cn.gov.zcy.ares.adrasteia.spi.operator.OperatorInfuseService;
import cn.gov.zcy.ares.adrasteia.spi.persistent.LogPersistService;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-12-14 17:43:36
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Import({SpiBeanRegister.class})
public @interface RegisterSpi {
    Class<?>[] register() default {OperatorInfuseService.class, LogPersistService.class};
}
