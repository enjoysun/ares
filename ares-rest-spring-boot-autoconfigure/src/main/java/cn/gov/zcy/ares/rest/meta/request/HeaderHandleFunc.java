package cn.gov.zcy.ares.rest.meta.request;

import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-11-30 10:49:30
 */
public interface HeaderHandleFunc {
    HttpHeaders func(MultiValueMap<String, String> header);
}
