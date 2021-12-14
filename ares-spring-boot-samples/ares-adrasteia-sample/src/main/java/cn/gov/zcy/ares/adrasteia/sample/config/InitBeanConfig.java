package cn.gov.zcy.ares.adrasteia.sample.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-12-09 20:34:49
 */
@Configuration
public class InitBeanConfig {

    @Bean
    public PersonAware getParsonAware() {
        return new PersonAware();
    }
}
