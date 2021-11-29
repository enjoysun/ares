package cn.gov.zcy.ares.rest.core.chain;

import cn.gov.zcy.ares.rest.meta.request.RequestStructure;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.util.Objects;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-11-29 19:48:59
 */
public interface ExecuteFilter extends ClientHttpRequestInterceptor {

    Logger logger = LoggerFactory.getLogger(ExecuteFilter.class);

    /**
     * 执行事件
     *
     * @param structure   请求体
     * @param filterChain 执行链
     */
    void doFilter(RequestStructure structure, FilterChain filterChain);

    @Override
    default ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        logger.info(String.format("请求拦截: method:%s,uri:%s,headers:%s,methodValue:%s",
                Objects.requireNonNull(httpRequest.getMethod()).name(),
                httpRequest.getURI().toString(),
                new ObjectMapper().writeValueAsString(httpRequest.getHeaders().toSingleValueMap()),
                httpRequest.getMethodValue()));
        return clientHttpRequestExecution.execute(httpRequest, bytes);
    }
}
