package cn.gov.zcy.ares.cache.config;

import cn.gov.zcy.ares.cache.support.AresCacheLettuceService;
import cn.gov.zcy.ares.cache.support.AresCacheProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-11-24 15:23:56
 */
@Configuration
@EnableConfigurationProperties(AresCacheProperties.class)
public class AresCacheAutoConfiguration {

    @Autowired
    private AresCacheProperties aresCacheProperties;

    @Bean
    public AresCacheLettuceService aresCacheLettuceService(){
        return new AresCacheLettuceService();
    }
}
