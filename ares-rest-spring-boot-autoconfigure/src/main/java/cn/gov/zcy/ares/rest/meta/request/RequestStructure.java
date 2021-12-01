package cn.gov.zcy.ares.rest.meta.request;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.lang.reflect.Type;
import java.util.*;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-11-29 15:06:10
 * * request-body
 */
public class RequestStructure<T, K> {
    public RequestStructure(Builder<T, K> builder) {
        this.requestUrl = builder.requestUrl;
        this.requestMethod = builder.requestMethod;
        this.header = builder.header;
        this.resultMapper = builder.resultMapper;
        this.params = builder.params;
        this.pathVariable = builder.pathVariable;
        this.payLoad = builder.payLoad;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void updateRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }


    public HttpMethod getRequestMethod() {
        return requestMethod;
    }


    public MultiValueMap<String, String> getHeader() {
        return header;
    }


    public Class<T> getResultMapper() {
        return resultMapper;
    }


    public Map<String, String> getParams() {
        return params;
    }


    public List<Object> getPathVariable() {
        return pathVariable;
    }


    /**
     * 请求地址
     */
    private String requestUrl;

    /**
     * 请求方法
     */
    private final HttpMethod requestMethod;

    /**
     * 请求头
     */
    private final MultiValueMap<String, String> header;

    /**
     * 映射结果类
     */
    private final Class<T> resultMapper;

    /**
     * 请求参数key， value
     */
    private final Map<String, String> params;

    /**
     * 占位参数
     */
    private final List<Object> pathVariable;

    public K getPayLoad() {
        return payLoad;
    }


    /**
     * post参数对象
     **/
    private final K payLoad;


    public static <T, K> Builder<T, K> builder() {
        return new Builder<>();
    }

    public static class Builder<T, K> {
        private String requestUrl;

        private HttpMethod requestMethod;

        private MultiValueMap<String, String> header;

        private Class<T> resultMapper;

        private Map<String, String> params;

        private List<Object> pathVariable;

        private K payLoad;

        public Builder<T, K> requestUrl(String requestUrl) {
            this.requestUrl = requestUrl;
            return this;
        }

        public Builder<T, K> payLoad(K payLoad) {
            this.payLoad = payLoad;
            return this;
        }

        public Builder<T, K> requestMethod(HttpMethod requestMethod) {
            this.requestMethod = requestMethod;
            return this;
        }

        public Builder<T, K> header(MultiValueMap<String, String> header) {
            this.header = header;
            return this;
        }

        public Builder<T, K> resultMapper(Class<T> resultMapper) {
            this.resultMapper = resultMapper;
            return this;
        }

        public Builder<T, K> params(Map<String, String> params) {
            this.params = params;
            return this;
        }

        public Builder<T, K> pathVariable(List<Object> pathVariable) {
            this.pathVariable = pathVariable;
            return this;
        }

        public RequestStructure<T, K> build() {
            return new RequestStructure<>(this);
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
        valueMap.add("ss", "23");
        System.out.println(valueMap.get("Accept-Encoding:"));
        HttpHeaders linkedHashMap = headerSplice(valueMap);
        System.out.println(linkedHashMap.get("Accept-Encoding:"));
        headerSplice(linkedHashMap);
    }
}
