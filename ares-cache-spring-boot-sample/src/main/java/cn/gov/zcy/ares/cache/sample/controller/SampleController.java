package cn.gov.zcy.ares.cache.sample.controller;

import cn.gov.zcy.ares.cache.support.AresCacheLettuceService;
import cn.gov.zcy.ares.cache.support.AresCacheProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-11-24 16:50:44
 */
@RestController
@RequestMapping("/sample/")
public class SampleController {

    @Autowired
    private AresCacheLettuceService cacheLettuceService;

    @Autowired
    private AresCacheProperties aresCacheProperties;

    @GetMapping("t1")
    public void sampleTest(){
        cacheLettuceService.test(aresCacheProperties);
    }
}
