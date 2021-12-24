package cn.gov.zcy.ares.adrasteia.meta;

import cn.gov.zcy.ares.adrasteia.annotation.LogRecord;
import cn.gov.zcy.ares.adrasteia.core.envluation.AresEvaluationContext;
import cn.gov.zcy.ares.adrasteia.core.envluation.ExpressionEnum;
import lombok.Builder;
import lombok.Data;


/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-12-14 18:02:51
 */
@Data
@Builder
public class ParserData<T> {
    private Class<? extends T> clazz;
    private ExpressionEnum expressionEnum;
    private String expressionText;
    private AresEvaluationContext evaluationContext;
}
