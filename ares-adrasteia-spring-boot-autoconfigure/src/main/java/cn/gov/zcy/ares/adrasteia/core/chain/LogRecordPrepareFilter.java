package cn.gov.zcy.ares.adrasteia.core.chain;

import cn.gov.zcy.ares.adrasteia.meta.LogPersistContext;
import org.aspectj.lang.JoinPoint;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-12-13 11:53:30
 * 责任链
 */
public interface LogRecordPrepareFilter {
    void doFilter(LogPersistContext persistContext, JoinPoint joinPoint, Object result, FilterChain chain);
}
