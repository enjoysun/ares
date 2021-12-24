package cn.gov.zcy.ares.adrasteia.core.parser;

import cn.gov.zcy.ares.adrasteia.core.envluation.AresValueParser;
import cn.gov.zcy.ares.adrasteia.meta.LogPersistContext;
import cn.gov.zcy.ares.adrasteia.meta.ParserData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.expression.AnnotatedElementKey;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-12-11 14:46:21
 */
@Slf4j
public abstract class AbstractParser {

    /**
     * 执行前操作
     */
    public abstract <T> boolean parserBefore(ParserData<T> parserData, LogPersistContext persistentContext);

    public <T> T invoke(AresValueParser valueParser, ParserData<T> parserData, LogPersistContext persistentContext) {
        try {
            boolean b = this.parserBefore(parserData, persistentContext);
            if (b) {
                AnnotatedElementKey annotatedElementKey = new AnnotatedElementKey(parserData.getEvaluationContext().getMethod(), parserData.getClazz());
                T parser = valueParser.valueParser(parserData.getExpressionEnum(), parserData.getEvaluationContext(), annotatedElementKey, parserData.getExpressionText(), parserData.getClazz());
                this.parserAfter(parser, persistentContext);
                return parser;
            }
        } catch (Throwable throwable) {
            String format = String.format("日志提取操作:%s执行失败，失败信息:%s", parserData.getExpressionEnum().getName(), throwable.getMessage());
            log.info(format);
            persistentContext.setErrorMsg(format);
        }
        return null;
    }

    /**
     * 执行后操作
     */
    public abstract <T> void parserAfter(T t, LogPersistContext persistentContext);
}
