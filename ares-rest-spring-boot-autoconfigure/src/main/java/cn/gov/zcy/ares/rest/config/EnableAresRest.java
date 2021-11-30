package cn.gov.zcy.ares.rest.config;

import cn.gov.zcy.ares.rest.annotation.ConditionalOnInterfaceMissingInstance;
import cn.gov.zcy.ares.rest.core.LarkRestTemplateBuilder;
import cn.gov.zcy.ares.rest.filter.DefaultRestRequestInterceptor;
import cn.gov.zcy.ares.rest.filter.RestRequestInterceptor;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-11-29 14:23:38
 */
@Configuration
@EnableConfigurationProperties({LarkRestTemplateProperties.class})
@Import(AresRestRegister.class)
public class EnableAresRest {

    @Bean
    @ConditionalOnInterfaceMissingInstance(baseClass = RestRequestInterceptor.class)
    public RestRequestInterceptor restRequestInterceptor() {
        return new DefaultRestRequestInterceptor();
    }

}
