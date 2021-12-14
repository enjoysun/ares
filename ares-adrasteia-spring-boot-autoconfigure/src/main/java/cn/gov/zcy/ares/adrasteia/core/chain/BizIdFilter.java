package cn.gov.zcy.ares.adrasteia.core.chain;

import cn.gov.zcy.ares.adrasteia.core.envluation.AresValueParser;
import cn.gov.zcy.ares.adrasteia.core.envluation.ExpressionEnum;
import cn.gov.zcy.ares.adrasteia.core.parser.BizParser;
import cn.gov.zcy.ares.adrasteia.meta.LogPersistContext;
import cn.gov.zcy.ares.adrasteia.meta.ParserData;
import org.aspectj.lang.JoinPoint;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-12-13 14:03:39
 */
public class BizIdFilter implements LogRecordPrepareFilter {

    public BizIdFilter(AresValueParser logValueParser, String bizNoExpressionText) {
        this.logValueParser = logValueParser;
        this.bizIdExpressionText = bizNoExpressionText;
    }

    private AresValueParser logValueParser;

    private String bizIdExpressionText;

    @Override
    public void doFilter(LogPersistContext persistContext, JoinPoint joinPoint, Object result, FilterChain chain) {
        ParserData<String> bizData = ParserData.<String>builder().expressionText(bizIdExpressionText).expressionEnum(ExpressionEnum.BIZ).joinPoint(joinPoint).clazz(String.class).joinPoint(joinPoint).result(result).build();
        new BizParser().invoke(logValueParser, bizData, persistContext);
        chain.doFilter(persistContext, joinPoint, result, chain);
    }
}
