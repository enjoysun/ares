package cn.gov.zcy.ares.rest.config;

import cn.gov.zcy.ares.rest.register.RestInterceptRegister;
import org.springframework.context.annotation.Import;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-11-30 11:13:15
 */
@Import({DefaultSSLFactory.class})
public class AresRestRegister {
}
