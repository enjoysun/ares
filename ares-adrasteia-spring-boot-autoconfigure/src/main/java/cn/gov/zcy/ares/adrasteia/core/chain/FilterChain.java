package cn.gov.zcy.ares.adrasteia.core.chain;

import cn.gov.zcy.ares.adrasteia.annotation.LogRecord;
import cn.gov.zcy.ares.adrasteia.core.envluation.AresEvaluationContext;
import cn.gov.zcy.ares.adrasteia.meta.LogPersistContext;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-12-13 11:55:33
 */
public class FilterChain implements LogRecordPrepareFilter {
    private AtomicInteger pos = new AtomicInteger(0);
    private List<LogRecordPrepareFilter> filters;

    public FilterChain(PersistLocal persistLocal) {
        this.persistLocal = persistLocal;
    }

    private PersistLocal persistLocal;

    public void addFilter(Collection<LogRecordPrepareFilter> chains) {
        if (null == filters) {
            filters = new ArrayList<>();
        }
        filters.addAll(chains);
    }


    @Override
    public void doFilter(LogRecord logRecord, AresEvaluationContext evaluationContext, LogPersistContext persistentContext, FilterChain chain) {

        if (pos.get() == filters.size()) {
            /*结束钩子*/
            persistLocal.invokePersist(persistentContext);
        }else {
            filters.get(pos.getAndIncrement()).doFilter(logRecord, evaluationContext, persistentContext, chain);
        }
    }
}
