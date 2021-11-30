package cn.gov.zcy.ares.rest.meta.request;

import org.springframework.http.HttpEntity;
import org.springframework.util.MultiValueMap;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-11-30 10:51:48
 */
public class LarkHttpEntity<ParLoad> extends HttpEntity<ParLoad> {

    public LarkHttpEntity(ParLoad body, MultiValueMap<String, String> header, HeaderHandleFunc headerHandleFunc) {
        super(body, headerHandleFunc.func(header));
    }
}
