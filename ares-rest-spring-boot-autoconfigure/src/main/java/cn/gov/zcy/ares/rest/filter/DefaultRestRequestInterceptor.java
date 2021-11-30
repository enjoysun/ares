package cn.gov.zcy.ares.rest.filter;

import cn.gov.zcy.ares.rest.annotation.ConditionalOnInterfaceMissingInstance;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.util.Objects;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-11-30 09:46:23
 */
@Slf4j
public class DefaultRestRequestInterceptor implements RestRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        log.info(String.format("请求拦截: method:%s,uri:%s,headers:%s,methodValue:%s",
                Objects.requireNonNull(httpRequest.getMethod()).name(),
                httpRequest.getURI().toString(),
                new ObjectMapper().writeValueAsString(httpRequest.getHeaders().toSingleValueMap()),
                httpRequest.getMethodValue()));
        return clientHttpRequestExecution.execute(httpRequest, bytes);
    }
}
