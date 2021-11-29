package cn.gov.zcy.ares.rest.meta.request;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.*;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-11-29 15:06:10
 * * request-body
 */
public class RequestStructure<T> {
    public RequestStructure(Builder<T> builder) {
        this.requestUrl = builder.requestUrl;
        this.requestMethod = builder.requestMethod;
        this.header = builder.header;
        this.resultMapper = builder.resultMapper;
        this.params = builder.params;
        this.pathVariable = builder.pathVariable;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public HttpMethod getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(HttpMethod requestMethod) {
        this.requestMethod = requestMethod;
    }

    public MultiValueMap<String, String> getHeader() {
        return header;
    }

    public void setHeader(MultiValueMap<String, String> header) {
        this.header = header;
    }

    public Class<T> getResultMapper() {
        return resultMapper;
    }

    public void setResultMapper(Class<T> resultMapper) {
        this.resultMapper = resultMapper;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public List<Object> getPathVariable() {
        return pathVariable;
    }

    public void setPathVariable(List<Object> pathVariable) {
        this.pathVariable = pathVariable;
    }

    /*
     * 请求地址
     * */
    private String requestUrl;

    /*
     * 请求方法
     * */
    private HttpMethod requestMethod;

    /*
     * 请求头
     * */
    private MultiValueMap<String, String> header;

    /*
     * 映射结果类
     * */
    private Class<T> resultMapper;

    /*
     * 请求参数key， value
     * */
    private Map<String, String> params;

    /*
     * 占位参数
     * */
    private List<Object> pathVariable;

    public static <T> Builder<T> builder() {
        return new Builder<T>();
    }

    public static class Builder<T> {
        private String requestUrl;

        private HttpMethod requestMethod;

        private MultiValueMap<String, String> header;

        private Class<T> resultMapper;

        private Map<String, String> params;

        private List<Object> pathVariable;

        public Builder<T> requestUrl(String requestUrl) {
            this.requestUrl = requestUrl;
            return this;
        }

        public Builder<T> requestMethod(HttpMethod requestMethod) {
            this.requestMethod = requestMethod;
            return this;
        }

        public Builder<T> header(MultiValueMap<String, String> header) {
            this.header = header;
            return this;
        }

        public Builder<T> resultMapper(Class<T> resultMapper) {
            this.resultMapper = resultMapper;
            return this;
        }

        public Builder<T> params(Map<String, String> params) {
            this.params = params;
            return this;
        }

        public Builder<T> pathVariable(List<Object> pathVariable) {
            this.pathVariable = pathVariable;
            return this;
        }

        public RequestStructure<T> build() {
            return new RequestStructure<T>(this);
        }
    }

    public static HttpHeaders headerSplice(MultiValueMap<String, String> header) {
        if (header instanceof HttpHeaders) {
            return (HttpHeaders) header;
        }
        HttpHeaders headers = new HttpHeaders();
        if (null != header && !header.isEmpty()) {
            header.forEach((key, value) -> {
                if (null != value && !value.isEmpty()) {
                    headers.put(key, value);
                }
            });
        }
        return headers;
    }

    public static void main(String[] args) {
        MultiValueMap<String, String> valueMap = new LinkedMultiValueMap<>();
        valueMap.add("Accept-Encoding:", "gzip");
        valueMap.add("Accept-Encoding:", "tar");
        valueMap.add("ss","23");
        System.out.println(valueMap.get("Accept-Encoding:"));
        HttpHeaders linkedHashMap = headerSplice(valueMap);
        System.out.println(linkedHashMap.get("Accept-Encoding:"));
        headerSplice(linkedHashMap);
    }
}
