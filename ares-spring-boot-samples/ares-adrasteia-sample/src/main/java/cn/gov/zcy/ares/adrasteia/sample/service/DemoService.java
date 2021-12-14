package cn.gov.zcy.ares.adrasteia.sample.service;

import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-12-14 13:53:09
 */
@Service
public class DemoService {
    public String getContext(String index) {
        return String.format("查询结果:%s", index);
    }
}
