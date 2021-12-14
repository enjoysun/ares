package cn.gov.zcy.ares.adrasteia.core.chain;

import cn.gov.zcy.ares.adrasteia.core.envluation.AresValueParser;
import cn.gov.zcy.ares.adrasteia.core.envluation.ExpressionEnum;
import cn.gov.zcy.ares.adrasteia.core.parser.IdentityTypeParser;
import cn.gov.zcy.ares.adrasteia.meta.LogPersistContext;
import cn.gov.zcy.ares.adrasteia.meta.ParserData;
import org.aspectj.lang.JoinPoint;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-12-13 19:08:30
 */
public class IdentityTypeFilter implements LogRecordPrepareFilter {
    public IdentityTypeFilter(AresValueParser logValueParser, String identityTypeExpressionText) {
        this.logValueParser = logValueParser;
        this.identityTypeExpressionText = identityTypeExpressionText;
    }

    private AresValueParser logValueParser;

    private String identityTypeExpressionText;

    @Override
    public void doFilter(LogPersistContext persistContext, JoinPoint joinPoint, Object result, FilterChain chain) {
        ParserData<String> bizData = ParserData.<String>builder().expressionText(identityTypeExpressionText).expressionEnum(ExpressionEnum.IDENTITYTYPE).joinPoint(joinPoint).clazz(String.class).joinPoint(joinPoint).result(result).build();
        new IdentityTypeParser().invoke(logValueParser, bizData, persistContext);
        chain.doFilter(persistContext, joinPoint, result, chain);
    }
}
