package cn.gov.zcy.ares.adrasteia.meta;

import cn.gov.zcy.ares.adrasteia.core.envluation.ExpressionEnum;
import lombok.Builder;
import lombok.Data;
import org.aspectj.lang.JoinPoint;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-12-14 18:02:51
 */
@Data
@Builder
public class ParserData<T> {
    private Class<? extends T> clazz;
    private ExpressionEnum expressionEnum;
    private JoinPoint joinPoint;
    private Object result;
    private String expressionText;
}
