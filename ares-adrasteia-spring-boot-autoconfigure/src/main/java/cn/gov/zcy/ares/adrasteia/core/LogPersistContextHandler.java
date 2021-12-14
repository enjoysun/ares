package cn.gov.zcy.ares.adrasteia.core;

import cn.gov.zcy.ares.adrasteia.meta.LogPersistContext;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-12-14 17:49:02
 */
public interface LogPersistContextHandler {
    void persistentBefore(LogPersistContext logPersistContext);
}
