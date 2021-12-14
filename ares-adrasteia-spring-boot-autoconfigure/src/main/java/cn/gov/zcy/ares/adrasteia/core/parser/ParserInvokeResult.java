package cn.gov.zcy.ares.adrasteia.core.parser;

import lombok.Data;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-12-11 14:59:02
 */
@Data
public class ParserInvokeResult<T> {
    private Boolean isSuccess;
    private Throwable throwable;
    private T result;
}
