package cn.gov.zcy.ares.adrasteia.meta;

import cn.gov.zcy.ares.adrasteia.meta.operator.Operator;
import lombok.Data;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-12-08 18:29:22
 */
@Data
public class LogPersistentContext {
    private Integer id;
    private Long bizId;
    private String[] childBizId;
    private Operator operator;
    private String identityType;
    private String traceId;
    private String content;
    private String actionNode;
    private String actionClassName;
    private String type;
    private String stashContext;
    private String condition;
    private boolean isConditionPass;
    private boolean invokeStatus;
    private String errorMsg;
}
