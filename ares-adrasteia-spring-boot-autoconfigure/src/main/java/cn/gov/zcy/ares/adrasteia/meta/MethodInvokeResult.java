package cn.gov.zcy.ares.adrasteia.meta;

import lombok.Data;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-12-09 11:46:05
 * 方法执行结果
 */

@Data
public class MethodInvokeResult {
    private boolean isInvokeSuccess;
    private String throwableMessage;
    private Throwable throwable;
}
