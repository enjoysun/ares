package cn.gov.zcy.ares.rest.meta.request;

import org.springframework.http.HttpMethod;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-11-29 14:42:31
 */
public enum RequestMethod {
    /**
     * 请求method返回枚举<seq>枚举值暂定</seq>
     * **/
    OPTIONS(1, "OPTIONS", "OPTIONS方法用于描述目标资源的通信选项"){
        @Override
        public HttpMethod getHttpMethod() {
            return HttpMethod.OPTIONS;
        }
    },
    GET(2,"GET","GET方法请求一个指定资源的表示形式，使用GET的请求应该只被用于获取数据"){
        @Override
        public HttpMethod getHttpMethod() {
            return HttpMethod.GET;
        }
    },
    HEAD(3,"HEAD","HEAD方法请求一个与GET请求的响应相同的响应，但没有响应体"){
        @Override
        public HttpMethod getHttpMethod() {
            return HttpMethod.HEAD;
        }
    },
    POST(4,"POST","POST方法用于将实体提交到指定的资源，通常导致在服务器上的状态变化或副作用"){
        @Override
        public HttpMethod getHttpMethod() {
            return HttpMethod.POST;
        }
    },
    PUT(5,"PUT","PUT方法用请求有效载荷替换目标资源的所有当前表示"){
        @Override
        public HttpMethod getHttpMethod() {
            return HttpMethod.PUT;
        }
    },
    DELETE(6,"DELETE","DELETE方法删除指定的资源"){
        @Override
        public HttpMethod getHttpMethod() {
            return HttpMethod.DELETE;
        }
    },
    PATCH(7,"PATCH","PATCH方法用于对资源应用部分修改"){
        @Override
        public HttpMethod getHttpMethod() {
            return HttpMethod.PATCH;
        }
    },
    TRACE(8,"TRACE","TRACE方法沿着到目标资源的路径执行一个消息环回测试"){
        @Override
        public HttpMethod getHttpMethod() {
            return HttpMethod.TRACE;
        }
    }
    ;

    public int getSequence() {
        return sequence;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public abstract HttpMethod getHttpMethod();
    private int sequence;
    private String name;
    private String description;
    RequestMethod(int sequence, String name, String description) {
        this.sequence = sequence;
        this.name = name;
        this.description = description;
    }
}
