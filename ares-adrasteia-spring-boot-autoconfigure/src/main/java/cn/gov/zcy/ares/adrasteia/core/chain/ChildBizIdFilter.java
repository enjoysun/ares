package cn.gov.zcy.ares.adrasteia.core.chain;

import cn.gov.zcy.ares.adrasteia.core.envluation.AresValueParser;
import cn.gov.zcy.ares.adrasteia.core.envluation.ExpressionEnum;
import cn.gov.zcy.ares.adrasteia.core.parser.ChildBizIdParser;
import cn.gov.zcy.ares.adrasteia.meta.LogPersistContext;
import cn.gov.zcy.ares.adrasteia.meta.ParserData;
import org.aspectj.lang.JoinPoint;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-12-13 19:02:45
 */
public class ChildBizIdFilter implements LogRecordPrepareFilter {
    public ChildBizIdFilter(AresValueParser logValueParser, String childBizIdExpressionText) {
        this.logValueParser = logValueParser;
        this.childBizIdExpressionText = childBizIdExpressionText;
    }

    private AresValueParser logValueParser;

    private String childBizIdExpressionText;

    @Override
    public void doFilter(LogPersistContext persistContext, JoinPoint joinPoint, Object result, FilterChain chain) {
        ParserData<String> bizData = ParserData.<String>builder().expressionText(childBizIdExpressionText).expressionEnum(ExpressionEnum.CHILDBIZID).joinPoint(joinPoint).clazz(String.class).joinPoint(joinPoint).result(result).build();
        new ChildBizIdParser().invoke(logValueParser, bizData, persistContext);
        chain.doFilter(persistContext, joinPoint, result, chain);
    }
}
