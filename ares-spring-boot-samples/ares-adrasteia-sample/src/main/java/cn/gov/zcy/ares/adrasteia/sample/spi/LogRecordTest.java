package cn.gov.zcy.ares.adrasteia.sample.spi;

import cn.gov.zcy.ares.adrasteia.meta.LogPersistContext;
import cn.gov.zcy.ares.adrasteia.sample.config.PersonAware;
import cn.gov.zcy.ares.adrasteia.spi.persistent.LogPersistService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-12-11 18:46:20
 */
public class LogRecordTest implements LogPersistService {
    @Autowired
    private PersonAware personAware;
    @Override
    public boolean persistent(LogPersistContext persistentContext) {
        System.out.println("记录");
        personAware.sayHobby("ok咯");
        return false;
    }
}
