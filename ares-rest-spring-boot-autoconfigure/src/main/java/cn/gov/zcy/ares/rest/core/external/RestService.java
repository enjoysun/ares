package cn.gov.zcy.ares.rest.core.external;

import cn.gov.zcy.ares.rest.meta.request.RequestStructure;
import cn.gov.zcy.ares.rest.meta.response.ResponseStructure;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-11-29 20:04:05
 */
public interface RestService {

    /**
     * 执行事件
     * @param requestStructure 请求实例
     * @param <Criteria> 返回值
     * */
    <Criteria, PayLoad> ResponseStructure<Criteria> invoke(RequestStructure<Criteria, PayLoad> requestStructure);
}
