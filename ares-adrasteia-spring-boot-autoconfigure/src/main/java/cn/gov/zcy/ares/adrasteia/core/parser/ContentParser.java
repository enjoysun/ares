package cn.gov.zcy.ares.adrasteia.core.parser;

import cn.gov.zcy.ares.adrasteia.meta.LogPersistContext;
import cn.gov.zcy.ares.adrasteia.meta.ParserData;
import org.apache.commons.lang3.StringUtils;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-12-11 15:29:12
 */
public class ContentParser extends AbstractParser {
    @Override
    public <T> boolean parserBefore(ParserData<T> parserData, LogPersistContext persistentContext) {
        if (StringUtils.isNotBlank(parserData.getExpressionText())) {
            return true;
        }
        persistentContext.setContent("");
        return false;
    }

    @Override
    public <T> void parserAfter(T t, LogPersistContext persistentContext) {
        persistentContext.setContent(String.valueOf(t));
    }

    private static class Content {
        private final static ContentParser CONTENT_PARSER;

        static {
            CONTENT_PARSER = new ContentParser();
        }

        public static ContentParser getInstance() {
            return CONTENT_PARSER;
        }
    }

    public static ContentParser getParser() {
        return Content.getInstance();
    }
}
