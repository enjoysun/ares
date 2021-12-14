package cn.gov.zcy.ares.adrasteia.sample.spi;


import cn.gov.zcy.ares.adrasteia.core.LogPersistContextHandler;
import cn.gov.zcy.ares.adrasteia.meta.LogPersistContext;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-12-13 10:38:31
 */
public class StashContextLogPersistBefore implements LogPersistContextHandler {
    @Override
    public void persistentBefore(LogPersistContext logPersistContext) {
        logPersistContext.setContext("1234567");
    }
}
