package cn.gov.zcy.ares.adrasteia.core.chain.filter;

import cn.gov.zcy.ares.adrasteia.annotation.LogRecord;
import cn.gov.zcy.ares.adrasteia.core.chain.FilterChain;
import cn.gov.zcy.ares.adrasteia.core.chain.LogRecordPrepareFilter;
import cn.gov.zcy.ares.adrasteia.core.envluation.AresEvaluationContext;
import cn.gov.zcy.ares.adrasteia.core.envluation.AresValueParser;
import cn.gov.zcy.ares.adrasteia.core.envluation.ExpressionEnum;
import cn.gov.zcy.ares.adrasteia.core.parser.ConditionParser;
import cn.gov.zcy.ares.adrasteia.meta.LogPersistContext;
import cn.gov.zcy.ares.adrasteia.meta.ParserData;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-12-22 16:25:58
 */
public class ConditionFilter implements LogRecordPrepareFilter {

    private AresValueParser aresValueParser;

    public ConditionFilter(AresValueParser aresValueParser) {
        this.aresValueParser = aresValueParser;
    }

    @Override
    public void doFilter(LogRecord logRecord, AresEvaluationContext evaluationContext, LogPersistContext persistentContext, FilterChain chain) {
        ParserData<Boolean> parserData = ParserData.<Boolean>builder().expressionEnum(ExpressionEnum.CONDITION).expressionText(logRecord.condition()).clazz(Boolean.class).evaluationContext(evaluationContext).build();
        ConditionParser.getParser().invoke(aresValueParser, parserData, persistentContext);
        if (persistentContext.isConditionPass()) {
            chain.doFilter(logRecord, evaluationContext, persistentContext, chain);
        }
    }
}
