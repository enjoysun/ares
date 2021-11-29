package cn.gov.zcy.ares.rest.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-11-29 14:23:38
 */
@Configuration
@EnableConfigurationProperties({LarkRestTemplateProperties.class})
@Import({})
public class LarkRestConfig {
}
