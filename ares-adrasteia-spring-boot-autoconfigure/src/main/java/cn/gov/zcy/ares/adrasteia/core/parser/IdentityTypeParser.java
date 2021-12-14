package cn.gov.zcy.ares.adrasteia.core.parser;

import cn.gov.zcy.ares.adrasteia.meta.LogPersistContext;
import cn.gov.zcy.ares.adrasteia.meta.ParserData;
import org.apache.commons.lang3.StringUtils;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-12-13 17:53:03
 */
public class IdentityTypeParser extends AbstractParser {
    @Override
    public <T> boolean parserBefore(ParserData<T> parserData, LogPersistContext persistentContext) {
        if (StringUtils.isNotBlank(parserData.getExpressionText())) {
            return true;
        }
        persistentContext.setIdentityType("");
        return false;
    }

    @Override
    public <T> void parserAfter(T t, LogPersistContext persistentContext) {
        persistentContext.setIdentityType(String.valueOf(t));
    }
}
