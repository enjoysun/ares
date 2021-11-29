package cn.gov.zcy.ares.rest.core.chain;

import cn.gov.zcy.ares.rest.meta.request.RequestStructure;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-11-29 19:52:57
 */
public class FilterChain implements ExecuteFilter {
    @Override
    public void doFilter(RequestStructure structure, FilterChain filterChain) {

    }
}
