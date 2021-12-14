package cn.gov.zcy.ares.adrasteia.sample.controller;

import cn.gov.zcy.ares.adrasteia.annotation.LogRecord;
import cn.gov.zcy.ares.adrasteia.core.LogPersistContextHandler;
import cn.gov.zcy.ares.adrasteia.meta.LogPersistContext;
import cn.gov.zcy.ares.adrasteia.sample.config.InitBeanConfig;
import cn.gov.zcy.ares.adrasteia.sample.config.PersonAware;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-12-08 18:46:43
 */
@RestController
@RequestMapping("/sample/adrasteia/")
public class SpelTestController {

    /**
     * 全文本压测案例
     * @param model 压测参数
     * @return responseModel 返回示例
     */
    @LogRecord(
            success = "执行签到操作,修改tag为:#{#model.getTag()};ids为:#{T(java.lang.String).join(',',#model.getIds())}",
            bizId = "#{#model.getBizId()}",
            childBizId = "#{#_resource.getChildBizId()}",
            context = "#{@demoService.getContext(#_resource.getContextIndex())}",
            identityType = "#{#model.getIdentity()}",
            persistBefore = StashContextLogPersistBefore.class)
    @PostMapping("full")
    public ResponseModel fullDemo(@RequestBody RequestModel model) {
        System.out.println("执行业务逻辑");
        ResponseModel responseModel = new ResponseModel();
        responseModel.setCode("200");
        responseModel.setRequestModel(model);
        responseModel.setChildBizId("biz_001");
        responseModel.setContextIndex("stash");
        return responseModel;
    }

    public static class StashContextLogPersistBefore implements LogPersistContextHandler {
        @Override
        public void persistentBefore(LogPersistContext logPersistContext) {
            logPersistContext.setContext(String.format("%s+1234567", logPersistContext.getContext()));
        }
    }

    @Data
    public static class RequestModel {
        /**
         * 测试tag
         * */
        private String tag;
        /**
         * 测试IDS
         * */
        private List<String> ids;
        /**
         * 测试项目ID
         * */
        private String bizId;
        /**
         * 测试身份标识
         * */
        private String identity;
    }

    @Data
    public static class ResponseModel {
        /**
         * 测试code
         * */
        private String code;
        /**
         * 测试入参
         * */
        private RequestModel requestModel;
        /**
         * 测试子业务ID
         * */
        private String childBizId;
        /**
         * 测试扩展上下文
         * */
        private String contextIndex;
    }
}
