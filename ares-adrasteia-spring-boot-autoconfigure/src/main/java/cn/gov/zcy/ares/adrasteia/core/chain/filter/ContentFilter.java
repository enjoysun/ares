package cn.gov.zcy.ares.adrasteia.core.chain.filter;

import cn.gov.zcy.ares.adrasteia.annotation.LogRecord;
import cn.gov.zcy.ares.adrasteia.core.chain.FilterChain;
import cn.gov.zcy.ares.adrasteia.core.chain.LogRecordPrepareFilter;
import cn.gov.zcy.ares.adrasteia.core.envluation.AresEvaluationContext;
import cn.gov.zcy.ares.adrasteia.core.envluation.AresValueParser;
import cn.gov.zcy.ares.adrasteia.core.envluation.ExpressionEnum;
import cn.gov.zcy.ares.adrasteia.core.parser.ContentParser;
import cn.gov.zcy.ares.adrasteia.meta.LogPersistContext;
import cn.gov.zcy.ares.adrasteia.meta.ParserData;
import org.apache.commons.lang3.StringUtils;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-12-13 14:09:05
 */
public class ContentFilter implements LogRecordPrepareFilter {

    public ContentFilter(AresValueParser logValueParser) {
        this.logValueParser = logValueParser;
    }

    private AresValueParser logValueParser;

    @Override
    public void doFilter(LogRecord logRecord, AresEvaluationContext evaluationContext, LogPersistContext persistentContext, FilterChain chain) {
        ParserData<String> parserData = ParserData.<String>builder().expressionEnum(ExpressionEnum.TEXT).evaluationContext(evaluationContext).expressionText(
                StringUtils.isEmpty(persistentContext.getErrorMsg()) ? logRecord.success() : logRecord.fail()
        ).clazz(String.class).build();
        ContentParser.getParser().invoke(logValueParser, parserData, persistentContext);
        chain.doFilter(logRecord, evaluationContext, persistentContext, chain);
    }
}
