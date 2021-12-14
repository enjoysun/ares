package cn.gov.zcy.ares.adrasteia.spi;

import cn.gov.zcy.ares.adrasteia.meta.LogPersistContext;
import cn.gov.zcy.ares.adrasteia.spi.persistent.LogPersistService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-12-11 17:53:16
 */
@Slf4j
public class DefaultLogPersistServiceImpl implements LogPersistService {
    @Override
    public boolean persistent(LogPersistContext persistentContext) {
        log.info("执行默认的持久化实现，不做任何操作");
        return false;
    }
}
