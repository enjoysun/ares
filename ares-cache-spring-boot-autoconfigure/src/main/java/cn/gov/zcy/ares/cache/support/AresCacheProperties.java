package cn.gov.zcy.ares.cache.support;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-11-24 15:34:52
 */
@ConfigurationProperties(prefix = "ares.cache")
public class AresCacheProperties {

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    /**脚手架测试**/
    private boolean enable = false;
}
