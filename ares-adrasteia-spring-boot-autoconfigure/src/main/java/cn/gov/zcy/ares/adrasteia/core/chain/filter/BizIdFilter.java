package cn.gov.zcy.ares.adrasteia.core.chain.filter;

import cn.gov.zcy.ares.adrasteia.annotation.LogRecord;
import cn.gov.zcy.ares.adrasteia.core.chain.FilterChain;
import cn.gov.zcy.ares.adrasteia.core.chain.LogRecordPrepareFilter;
import cn.gov.zcy.ares.adrasteia.core.envluation.AresEvaluationContext;
import cn.gov.zcy.ares.adrasteia.core.envluation.AresValueParser;
import cn.gov.zcy.ares.adrasteia.core.envluation.ExpressionEnum;
import cn.gov.zcy.ares.adrasteia.core.parser.BizParser;
import cn.gov.zcy.ares.adrasteia.meta.LogPersistContext;
import cn.gov.zcy.ares.adrasteia.meta.ParserData;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-12-13 14:03:39
 */
public class BizIdFilter implements LogRecordPrepareFilter {

    public BizIdFilter(AresValueParser logValueParser) {
        this.logValueParser = logValueParser;
    }

    private AresValueParser logValueParser;

    @Override
    public void doFilter(LogRecord logRecord, AresEvaluationContext evaluationContext, LogPersistContext persistentContext, FilterChain chain) {
        ParserData<String> bizData = ParserData.<String>builder().expressionText(logRecord.bizId()).expressionEnum(ExpressionEnum.BIZ).evaluationContext(evaluationContext).clazz(String.class).build();
        BizParser.getParser().invoke(logValueParser, bizData, persistentContext);
        chain.doFilter(logRecord, evaluationContext, persistentContext, chain);
    }
}
