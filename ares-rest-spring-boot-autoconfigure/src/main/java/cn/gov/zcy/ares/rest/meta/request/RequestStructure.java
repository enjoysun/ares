package cn.gov.zcy.ares.rest.meta.request;

import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-11-29 15:06:10
 * * request-body
 */
public class RequestStructure {
    public RequestStructure(Builder builder) {
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

    public Class<?> getResultMapper() {
        return resultMapper;
    }

    public void setResultMapper(Class<?> resultMapper) {
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
    private Class<?> resultMapper;

    /*
    * 请求参数key， value
    * */
    private Map<String, String> params;

    /*
    * 占位参数
    * */
    private List<Object> pathVariable;

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String requestUrl;

        private HttpMethod requestMethod;

        private MultiValueMap<String, String> header;

        private Class<?> resultMapper;

        private Map<String, String> params;

        private List<Object> pathVariable;

        public Builder requestUrl(String requestUrl) {
            this.requestUrl = requestUrl;
            return this;
        }

        public Builder requestMethod(HttpMethod requestMethod) {
            this.requestMethod = requestMethod;
            return this;
        }

        public Builder header(MultiValueMap<String, String> header) {
            this.header = header;
            return this;
        }

        public Builder resultMapper(Class<?> resultMapper) {
            this.resultMapper = resultMapper;
            return this;
        }

        public Builder params(Map<String, String> params) {
            this.params = params;
            return this;
        }

        public Builder pathVariable(List<Object> pathVariable) {
            this.pathVariable = pathVariable;
            return this;
        }

        public RequestStructure build() {
            return new RequestStructure(this);
        }
    }
}
