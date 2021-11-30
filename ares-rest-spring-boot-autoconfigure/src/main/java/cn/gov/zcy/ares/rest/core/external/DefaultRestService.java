package cn.gov.zcy.ares.rest.core.external;

import cn.gov.zcy.ares.rest.exception.RestErrorException;
import cn.gov.zcy.ares.rest.meta.request.LarkHttpEntity;
import cn.gov.zcy.ares.rest.meta.request.RequestStructure;
import cn.gov.zcy.ares.rest.meta.response.ResponseStructure;
import org.apache.http.conn.ConnectTimeoutException;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.SocketTimeoutException;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-11-29 20:13:34
 */
public class DefaultRestService implements RestServiceInterface {

    public DefaultRestService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private RestTemplate restTemplate;

    @Override
    public <Criteria, PayLoad> ResponseStructure<Criteria> invoke(RequestStructure<Criteria, PayLoad> requestStructure) {
        Assert.notNull(requestStructure, "'request' must not be null");
        Assert.notNull(requestStructure.getRequestUrl(), "'url' must not be null");
        Assert.notNull(requestStructure.getRequestMethod(), "'method' must not be null");
        if (null != requestStructure.getParams()) {
            // 构建queryString
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(requestStructure.getRequestUrl());
            requestStructure.getParams().forEach(builder::queryParam);
            requestStructure.setRequestUrl(builder.build().encode().toString());
        }
        ResponseEntity<Criteria> exchange;
        ResponseStructure<Criteria> resp = new ResponseStructure<>();
        MultiValueMap<String, String> header = requestStructure.getHeader();
        header.set("Content-Type", "application/json;q=0.2,charset=UTF-8;q=0.2");
        LarkHttpEntity<PayLoad> payLoadLarkHttpEntity = new LarkHttpEntity<>(requestStructure.getPayLoad(), header, RequestStructure::headerSplice);
        try {
            exchange = restTemplate.exchange(requestStructure.getRequestUrl(), requestStructure.getRequestMethod(), payLoadLarkHttpEntity, requestStructure.getResultMapper(), requestStructure.getPathVariable());
            resp.setRepCode(exchange.getStatusCodeValue());
            resp.setRepBody(exchange.getBody());
            resp.setHttpHeaders(exchange.getHeaders());
        } catch (Throwable throwable) {
            if (throwable.getCause() instanceof ConnectTimeoutException) {
                resp.setRepCode(-1);
                ConnectTimeoutException connectTimeoutException = (ConnectTimeoutException) throwable.getCause();
                resp.setRepMsg(String.format("HOST:%s,METHOD:%s,ERROR:%s", connectTimeoutException.getHost(), requestStructure.getRequestMethod().name(), connectTimeoutException.getMessage()));
            } else if (throwable.getCause() instanceof SocketTimeoutException) {
                resp.setRepCode(-2);
                SocketTimeoutException socketTimeoutException = (SocketTimeoutException) throwable.getCause();
                resp.setRepMsg(String.format("HOST:%s,METHOD:%s,ERROR:%s", requestStructure.getRequestUrl(), requestStructure.getRequestMethod().name(), socketTimeoutException.getMessage()));
            } else if (throwable instanceof RestErrorException) {
                resp.setRepCode(-3);
                resp.setRepMsg(String.format("HOST:%s,METHOD:%s,ERROR:%s", requestStructure.getRequestUrl(), requestStructure.getRequestMethod().name(), throwable.getMessage()));
            } else {
                resp.setRepCode(0);
                resp.setRepMsg(String.format("HOST:%s,METHOD:%s,ERROR:%s", requestStructure.getRequestUrl(), requestStructure.getRequestMethod().name(), throwable.getMessage()));
            }
        }
        return resp;
    }
}
