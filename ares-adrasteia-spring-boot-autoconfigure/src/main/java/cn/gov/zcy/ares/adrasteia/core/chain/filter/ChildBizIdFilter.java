package cn.gov.zcy.ares.adrasteia.core.chain.filter;

import cn.gov.zcy.ares.adrasteia.annotation.LogRecord;
import cn.gov.zcy.ares.adrasteia.core.chain.FilterChain;
import cn.gov.zcy.ares.adrasteia.core.chain.LogRecordPrepareFilter;
import cn.gov.zcy.ares.adrasteia.core.envluation.AresEvaluationContext;
import cn.gov.zcy.ares.adrasteia.core.envluation.AresValueParser;
import cn.gov.zcy.ares.adrasteia.core.envluation.ExpressionEnum;
import cn.gov.zcy.ares.adrasteia.core.parser.ChildBizIdParser;
import cn.gov.zcy.ares.adrasteia.meta.LogPersistContext;
import cn.gov.zcy.ares.adrasteia.meta.ParserData;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-12-13 19:02:45
 */
public class ChildBizIdFilter implements LogRecordPrepareFilter {

    public ChildBizIdFilter(AresValueParser logValueParser) {
        this.logValueParser = logValueParser;
    }

    private AresValueParser logValueParser;


    @Override
    public void doFilter(LogRecord logRecord, AresEvaluationContext evaluationContext, LogPersistContext persistentContext, FilterChain chain) {
        ParserData<String> parserData = ParserData.<String>builder().expressionText(logRecord.childBizId()).expressionEnum(ExpressionEnum.CHILD_BIZ_ID).clazz(String.class).evaluationContext(evaluationContext).build();
        ChildBizIdParser.getParser().invoke(logValueParser, parserData, persistentContext);
        chain.doFilter(logRecord, evaluationContext, persistentContext, chain);
    }
}
