package cn.gov.zcy.ares.adrasteia.annotation;

import java.lang.annotation.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
     * 当前操作人标识
     */
    String operator() default "";

    /**
     * 项目号
     */
    long bixNo();

    /**
     * 操作节点
     */
    String actionNode() default "";

    /**
     * 扩展参数
     */
    String params() default "";


    /**
     * 触发条件
     */
    String condition() default "";

    /**
     * 身份类别
     */
    String identityType() default "";

    /**
     * 子业务编号
     */
    String[] childBizId() default "";

    /**
     * request-id
     */
    String traceId() default "";

    /**
     * 数据流转类型
     */
    String type() default "";

    /**
     * 退回版本ID
     */
    String stashContext() default "";
}
