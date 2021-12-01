package cn.gov.zcy.ares.rest.sample.controller;

import cn.gov.zcy.ares.rest.core.external.RestService;
import cn.gov.zcy.ares.rest.meta.request.RequestStructure;
import cn.gov.zcy.ares.rest.meta.response.ResponseStructure;
import com.google.gson.reflect.TypeToken;
import lombok.Data;
import org.apache.http.client.methods.HttpHead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-12-01 19:35:06
 */
@RestController
@RequestMapping("/sample/rest/")
public class DefaultBaseRestTestController {

    @Autowired
    private RestService restService;

    @GetMapping("get")
    public void defaultGetService(){
        //header
        MultiValueMap<String, String> httpHead = new LinkedMultiValueMap<>();
        httpHead.set("Cookie", "");
        //params
        Map<String, String> params = new HashMap<>();
        params.put("page", "1");
        params.put("size", "10");
        Response<PageResult<VideoVo>> response = new Response<>();
        RequestStructure structure = RequestStructure.<Response<PageResult<VideoVo>>, VideoVo>builder()
                .header(httpHead)
                .params(params)
                .requestMethod(HttpMethod.GET)
                .requestUrl("http://127.0.0.1:8142/bidding/ms/video/conference/institutions/query")
//                .resultMapper(Response<>)
                .build();
        restService.invoke(structure);
        System.out.println("ok");
    }

    @Data
    private static class Response<T>{
        private boolean success;
        private T result;
        private String code;
        private String message;
    }

    @Data
    private static class PageResult<T>{
        private T data;
        private long total;
    }

    @Data
    private static class VideoVo{
        /**
         * 机构 id
         */
        private Long id;
        /**
         * 机构名称
         */
        private String name;
        /**
         * 机构类型
         **/
        private String userType;

        /**
         * 所在区划
         **/
        private String tenantName;
        /**
         * 是否启用视频会议
         */
        private Boolean enableVideoConference;
        /**
         * 配置视频会议的时间
         */
        private Date createdAt;
    }
}
