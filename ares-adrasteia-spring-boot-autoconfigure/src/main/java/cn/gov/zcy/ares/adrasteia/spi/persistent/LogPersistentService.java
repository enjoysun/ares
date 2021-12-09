package cn.gov.zcy.ares.adrasteia.spi.persistent;

import cn.gov.zcy.ares.adrasteia.meta.LogPersistentContext;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-12-08 18:33:36
 */
public interface LogPersistentService {
    /**
     * 日志信息持久化
     * */
    boolean persistent(LogPersistentContext persistentContext);
}
