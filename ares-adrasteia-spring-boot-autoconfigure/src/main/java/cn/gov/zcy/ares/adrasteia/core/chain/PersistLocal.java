package cn.gov.zcy.ares.adrasteia.core.chain;

import cn.gov.zcy.ares.adrasteia.core.LogPersistContextHandler;
import cn.gov.zcy.ares.adrasteia.core.parser.PersistBeforeFactory;
import cn.gov.zcy.ares.adrasteia.meta.LogPersistContext;
import cn.gov.zcy.ares.adrasteia.spi.persistent.LogPersistService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-12-13 13:55:12
 */
@Slf4j
public class PersistLocal {

    private LogPersistService logPersistService;

    public PersistLocal(LogPersistService logPersistService, Class<? extends LogPersistContextHandler> persistBefore) {
        this.logPersistService = logPersistService;
        this.persistBefore = persistBefore;
    }

    private Class<? extends LogPersistContextHandler> persistBefore;

    public void invokePersist(LogPersistContext logPersistContext) {
        if (null != logPersistService) {
            PersistBeforeFactory.getInvoke(persistBefore, logPersistContext);
            boolean persistent = logPersistService.persistent(logPersistContext);
            log.info(String.format("日志持久化执行状态:%s", persistent));
        }
    }
}
