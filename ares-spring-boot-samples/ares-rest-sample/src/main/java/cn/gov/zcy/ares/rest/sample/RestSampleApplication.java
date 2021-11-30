package cn.gov.zcy.ares.rest.sample;

import cn.gov.zcy.ares.rest.annotation.EnableRestIntercept;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-11-29 14:18:25
 */
@EnableRestIntercept(basePackages = {"cn.gov.zcy.ares.rest.sample.intercept"})
@SpringBootApplication
public class RestSampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestSampleApplication.class, args);
    }
}
