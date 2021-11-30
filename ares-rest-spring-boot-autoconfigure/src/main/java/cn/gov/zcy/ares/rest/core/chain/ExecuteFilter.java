package cn.gov.zcy.ares.rest.core.chain;

import cn.gov.zcy.ares.rest.meta.request.RequestStructure;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-11-29 19:48:59
 */
public interface ExecuteFilter  {

    /**
     * invoke 执行事件拦截
     *
     * @param structure   请求体
     * @param filterChain 执行链
     */
    void doFilter(RequestStructure structure, FilterChain filterChain);
}
