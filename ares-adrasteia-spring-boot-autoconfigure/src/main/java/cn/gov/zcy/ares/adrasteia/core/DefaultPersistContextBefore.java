package cn.gov.zcy.ares.adrasteia.core;

import cn.gov.zcy.ares.adrasteia.meta.LogPersistContext;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-12-14 17:50:28
 */
public class DefaultPersistContextBefore implements LogPersistContextHandler {
    @Override
    public void persistentBefore(LogPersistContext logPersistContext) {
        System.out.println("执行默认前置处理");
        return;
    }
}
