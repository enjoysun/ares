package cn.gov.zcy.ares.adrasteia.core.chain.filter;

import cn.gov.zcy.ares.adrasteia.annotation.LogRecord;
import cn.gov.zcy.ares.adrasteia.core.chain.FilterChain;
import cn.gov.zcy.ares.adrasteia.core.chain.LogRecordPrepareFilter;
import cn.gov.zcy.ares.adrasteia.core.envluation.AresEvaluationContext;
import cn.gov.zcy.ares.adrasteia.core.envluation.AresValueParser;
import cn.gov.zcy.ares.adrasteia.core.envluation.ExpressionEnum;
import cn.gov.zcy.ares.adrasteia.core.parser.IdentityTypeParser;
import cn.gov.zcy.ares.adrasteia.meta.LogPersistContext;
import cn.gov.zcy.ares.adrasteia.meta.ParserData;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-12-13 19:08:30
 */
public class IdentityTypeFilter implements LogRecordPrepareFilter {

    public IdentityTypeFilter(AresValueParser logValueParser) {
        this.logValueParser = logValueParser;
    }

    private AresValueParser logValueParser;


    @Override
    public void doFilter(LogRecord logRecord, AresEvaluationContext evaluationContext, LogPersistContext persistentContext, FilterChain chain) {
        ParserData<String> parserData = ParserData.<String>builder().expressionEnum(ExpressionEnum.IDENTITY_TYPE).evaluationContext(evaluationContext).clazz(String.class).expressionText(logRecord.identityType()).build();
        IdentityTypeParser.getParser().invoke(logValueParser, parserData, persistentContext);
        chain.doFilter(logRecord, evaluationContext, persistentContext, chain);
    }
}
