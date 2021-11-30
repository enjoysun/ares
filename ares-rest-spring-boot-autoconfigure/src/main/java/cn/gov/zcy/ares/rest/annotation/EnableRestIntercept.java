package cn.gov.zcy.ares.rest.annotation;

import cn.gov.zcy.ares.rest.register.RestInterceptRegister;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-11-30 13:34:39
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import(RestInterceptRegister.class)
public @interface EnableRestIntercept {

    /**
     * 拦截器扫描地址配置: [cn.gov.xx.xxx,cn.gov.xx.xxx1]
     * */
    String[] basePackages();
}
