package cn.gov.zcy.ares.adrasteia.core.chain;

import cn.gov.zcy.ares.adrasteia.core.envluation.AresValueParser;
import cn.gov.zcy.ares.adrasteia.core.envluation.ExpressionEnum;
import cn.gov.zcy.ares.adrasteia.core.parser.ContentParser;
import cn.gov.zcy.ares.adrasteia.meta.LogPersistContext;
import cn.gov.zcy.ares.adrasteia.meta.ParserData;
import org.aspectj.lang.JoinPoint;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-12-13 14:09:05
 */
public class ContentFilter implements LogRecordPrepareFilter {
    public ContentFilter(AresValueParser logValueParser, String contentExpressionText) {
        this.logValueParser = logValueParser;
        this.contentExpressionText = contentExpressionText;
    }

    private AresValueParser logValueParser;

    private String contentExpressionText;
    @Override
    public void doFilter(LogPersistContext persistContext, JoinPoint joinPoint, Object result, FilterChain chain) {
        ParserData<String> contentData = ParserData.<String>builder().expressionText(contentExpressionText).expressionEnum(ExpressionEnum.TEXT).joinPoint(joinPoint).clazz(String.class).joinPoint(joinPoint).result(result).build();
        new ContentParser().invoke(logValueParser, contentData, persistContext);
        chain.doFilter(persistContext, joinPoint, result, chain);
    }
}
