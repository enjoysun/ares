package cn.gov.zcy.ares.cache.support;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-11-24 15:25:25
 */
public class AresCacheLettuceService {
    public void test(AresCacheProperties cacheProperties){
        System.out.println(cacheProperties.isEnable());
    }
}
