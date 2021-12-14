package cn.gov.zcy.ares.adrasteia.meta;

import cn.gov.zcy.ares.adrasteia.meta.operator.Operator;
import lombok.Data;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-12-08 18:29:22
 */
@Data
public class LogPersistContext {
    /**
     * id
     */
    private Integer id;
    /**
     * 业务ID
     */
    private String bizId;
    /**
     * 子业务ID
     */
    private String childBizId;
    /**
     * 操作人
     */
    private Operator operator;
    /**
     * 身份标识扩展
     */
    private String identityType;
    /**
     * 跟踪链路
     */
    private String traceId;
    /**
     * 日志文案解析文本
     */
    private String content;
    /**
     * 操作节点
     */
    private String actionNode;
    /**
     * 操作类全路径
     */
    private String actionClassName;
    /**
     * 操作类型
     */
    private String actionType;
    /**
     * 扩展上下文
     */
    private String context;
    /**
     * 开启日志拦截条件表达式
     */
    private String condition;
    /**
     * 是否开启日志拦截
     */
    private boolean isConditionPass;
    /**
     * 解析持久化执行状态
     */
    private boolean invokeStatus;
    /**
     * 错误信息
     */
    private String errorMsg;
}
