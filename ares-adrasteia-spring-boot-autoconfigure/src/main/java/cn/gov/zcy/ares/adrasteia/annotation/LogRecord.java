package cn.gov.zcy.ares.adrasteia.annotation;

import cn.gov.zcy.ares.adrasteia.core.DefaultPersistContextBefore;
import cn.gov.zcy.ares.adrasteia.core.LogPersistContextHandler;

import java.lang.annotation.*;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-12-08 16:20:40
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface LogRecord {

    /**
     * 执行成功文案模板
     */
    String success();

    /**
     * 执行失败文案模板
     */
    String fail() default "";

    /**
     * 业务ID --spel
     */
    String bizId() default "";

    /**
     * 子业务编号 --spel
     */
    String childBizId() default "";

    /**
     * 操作类型
     */
    String actionType() default "";

    /**
     * 操作节点
     */
    String actionNode() default "";

    /**
     * 身份类别 --spel
     */
    String identityType() default "";

    /**
     * 扩展参数
     */
    String params() default "";

    /**
     * 触发条件
     */
    String condition() default "#{true}";

    /**
     * request-id
     */
    String traceId() default "";

    /**
     * 退回版本ID --spel
     */
    String context() default "";

    /**
     * 持久化之前行为
     * */
    Class<? extends LogPersistContextHandler> persistBefore() default DefaultPersistContextBefore.class;

}
