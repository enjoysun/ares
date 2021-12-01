package cn.gov.zcy.ares.rest.config;

import cn.gov.zcy.ares.rest.core.external.DefaultRestService;
import cn.gov.zcy.ares.rest.core.external.RestService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-11-29 14:23:38
 */
@Configuration
@EnableConfigurationProperties({LarkRestTemplateProperties.class})
@Import(AresRestRegister.class)
public class EnableAresRest {

    @Bean
    public RestService restService(RestTemplate restTemplate) {
        return new DefaultRestService(restTemplate);
    }
}
