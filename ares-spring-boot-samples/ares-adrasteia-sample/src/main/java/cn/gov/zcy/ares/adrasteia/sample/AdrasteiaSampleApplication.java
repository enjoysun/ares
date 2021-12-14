package cn.gov.zcy.ares.adrasteia.sample;

import cn.gov.zcy.ares.adrasteia.annotation.EnableLogRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-12-08 18:41:17
 */
@EnableLogRecord
@SpringBootApplication
public class AdrasteiaSampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdrasteiaSampleApplication.class, args);
    }
}
