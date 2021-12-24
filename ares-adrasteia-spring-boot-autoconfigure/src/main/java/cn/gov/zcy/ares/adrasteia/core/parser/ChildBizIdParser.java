package cn.gov.zcy.ares.adrasteia.core.parser;


import cn.gov.zcy.ares.adrasteia.meta.LogPersistContext;
import cn.gov.zcy.ares.adrasteia.meta.ParserData;
import org.apache.commons.lang3.StringUtils;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-12-13 17:52:33
 */
public class ChildBizIdParser extends AbstractParser {
    @Override
    public <T> boolean parserBefore(ParserData<T> parserData, LogPersistContext persistentContext) {
        if (StringUtils.isNotBlank(parserData.getExpressionText())) {
            return true;
        }
        persistentContext.setChildBizId("");
        return false;
    }

    @Override
    public <T> void parserAfter(T t, LogPersistContext persistentContext) {
        persistentContext.setChildBizId(String.valueOf(t));
    }

    private static class ChildBiz {
        private final static ChildBizIdParser CHILD_BIZ_ID_PARSER;

        static {
            CHILD_BIZ_ID_PARSER = new ChildBizIdParser();
        }

        public static ChildBizIdParser getInstance() {
            return CHILD_BIZ_ID_PARSER;
        }
    }

    public static ChildBizIdParser getParser() {
        return ChildBiz.getInstance();
    }
}
