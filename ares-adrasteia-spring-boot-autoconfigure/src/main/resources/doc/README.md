#### Log-Record  

> 作用于方法级别的日志截获，可根据方法入参和结果(_resource)，支持日志文案动态模板运算，一定程度上将业务代码与日志模式分离。支持注解和自定义函数、日志占位上下文<暂不成熟且侵入业务代码>。


#### 基础依赖  

|package|version|  
|---|---|  
|org.apache.commons:commons-lang3|3.7+|  
|org.projectlombok:lombok|1.16+|  
|org.springframework.boot:spring-boot-dependencies|2.0.3+|  

```xml
        <dependency>
            <groupId>cn.gov.zcy</groupId>
            <artifactId>log-record-spring-boot-starter</artifactId>
            <version>1.0.0-SNAPSHOT</version>
        </dependency>
```  

#### 接入说明  

> 1.开启功能，在启动类上加入@EnableLogRecord  
> 2.在目标方法上使用@LogRecord,注解属性说明请参照源码  

> 使用可参照log-record-spring-boot-sample项目中SpelCaseController,该类中包含使用示例   


###### 最佳实践  

```java
@RestController
@RequestMapping("/zeus/api/evaluate/log/pressure/test/")
public class PressureTestController {
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

@Service
public class DemoService {
    public String getContext(String index) {
        return String.format("查询结果:%s", index);
    }
}
```  

###### spi接入案例  

```java
public class PersistLogRecordPenetrate implements LogPersistService {

    @Autowired
    private LogRecordService logRecordService;
    /**
     * 日志持久化
     * logPersistContext:持久化上下文
     */
    @Override
    public boolean persistent(LogPersistContext logPersistContext) {
        OperatorLogDTO operatorLogDTO = new OperatorLogDTO();
        operatorLogDTO.setBizId(logPersistContext.getBizId());
        operatorLogDTO.setChildBizId(logPersistContext.getChildBizId());
        if (null!=logPersistContext.getOperator()) {
            operatorLogDTO.setOperatorId(logPersistContext.getOperator().getId());
            operatorLogDTO.setOperatorName(logPersistContext.getOperator().getName());
            operatorLogDTO.setOperatorType(logPersistContext.getOperator().getUserType());
            operatorLogDTO.setInstitutionId(logPersistContext.getOperator().getInstitutionId());
            operatorLogDTO.setInstitutionName(logPersistContext.getOperator().getInstitutionName());
        }
        operatorLogDTO.setIdentityType(logPersistContext.getIdentityType());
        operatorLogDTO.setTraceId(logPersistContext.getTraceId());
        operatorLogDTO.setContent(logPersistContext.getContent());
        operatorLogDTO.setActionNode(logPersistContext.getActionNode());
        operatorLogDTO.setActionClassName(logPersistContext.getActionClassName());
        operatorLogDTO.setActionType(logPersistContext.getActionType());
        operatorLogDTO.setContext(logPersistContext.getContext());
        return logRecordService.saveLogRecord(operatorLogDTO);
    }
}

public class LogOperatorInfuseConfiguration implements OperatorInfuseService {
    @Override
    public Operator getOperator() {

        return OperatorConverter.CONVERTER.convertLogOperator(OperatorManager.currentOperator());
    }
}  

// 需要在启动resources资源文件夹下新建META-INF/services文件夹，文件和内容如下
// cn.gov.zcy.log.record.spi.operator.OperatorInfuseService:cn.gov.zcy.zeus.web.configuration.LogOperatorInfuseConfiguration
// cn.gov.zcy.log.record.spi.persist.LogPersistService:cn.gov.zcy.zeus.service.record.PersistLogRecordPenetrate
```