package cn.gov.zcy.ares.adrasteia.core.chain;

import cn.gov.zcy.ares.adrasteia.meta.LogPersistContext;
import org.aspectj.lang.JoinPoint;

import java.util.ArrayList;
import java.util.Arrays;
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

    public void addFilter(LogRecordPrepareFilter... chains) {
        if (null == filters) {
            filters = new ArrayList<>();
        }
        filters.addAll(Arrays.asList(chains));
    }

    @Override
    public void doFilter(LogPersistContext persistContext, JoinPoint joinPoint, Object result, FilterChain chain) {
        if (pos.get() == filters.size()) {
            persistLocal.invokePersist(persistContext);
        } else {
            filters.get(pos.getAndIncrement()).doFilter(persistContext, joinPoint, result, chain);
        }
    }
}
