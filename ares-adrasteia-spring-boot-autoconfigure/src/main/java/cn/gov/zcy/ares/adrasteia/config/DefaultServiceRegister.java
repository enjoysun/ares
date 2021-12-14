package cn.gov.zcy.ares.adrasteia.config;

import cn.gov.zcy.ares.adrasteia.spi.DefaultLogPersistServiceImpl;
import cn.gov.zcy.ares.adrasteia.spi.DefaultOperatorInfuseServiceImpl;
import cn.gov.zcy.ares.adrasteia.spi.operator.OperatorInfuseService;
import cn.gov.zcy.ares.adrasteia.spi.persistent.LogPersistService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-12-14 17:55:58
 */
public class DefaultServiceRegister {
    @Bean
    @ConditionalOnMissingBean(OperatorInfuseService.class)
    public OperatorInfuseService operatorInfuseService() {
        return new DefaultOperatorInfuseServiceImpl();
    }

    @Bean
    @ConditionalOnMissingBean(LogPersistService.class)
    public LogPersistService logPersistentService() {
        return new DefaultLogPersistServiceImpl();
    }
}
