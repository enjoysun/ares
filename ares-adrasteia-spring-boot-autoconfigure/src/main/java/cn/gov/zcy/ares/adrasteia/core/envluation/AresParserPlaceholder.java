package cn.gov.zcy.ares.adrasteia.core.envluation;

import org.springframework.expression.ParserContext;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-12-08 20:00:12
 */
public class AresParserPlaceholder {

    private static class LogParserContext implements ParserContext {
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

    private static class ParserContextInstance {
        private final static ParserContext PARSER_CONTEXT;

        static {
            PARSER_CONTEXT = new LogParserContext();
        }

        public static ParserContext getParserContext() {
            return PARSER_CONTEXT;
        }
    }

    public static ParserContext getInstance() {
        return ParserContextInstance.getParserContext();
    }
}
