package cn.gov.zcy.ares.adrasteia.core.parser;

import org.springframework.expression.ParserContext;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-12-08 20:00:12
 */
public class AresParserPlaceholder implements ParserContext {

    @Override
    public boolean isTemplate() {
        return true;
    }

    @Override
    public String getExpressionPrefix() {
        return "#{";
    }

    @Override
    public String getExpressionSuffix() {
        return "}";
    }
}
