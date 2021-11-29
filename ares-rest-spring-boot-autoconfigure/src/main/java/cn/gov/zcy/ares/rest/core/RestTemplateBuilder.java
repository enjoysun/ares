package cn.gov.zcy.ares.rest.core;

import cn.gov.zcy.ares.rest.config.LarkRestTemplateProperties;
import org.apache.http.HttpHost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-11-29 19:29:53
 */
@Configuration
public class RestTemplateBuilder {

    private final LarkRestTemplateProperties properties;


    @Autowired
    private LayeredConnectionSocketFactory layeredConnectionSocketFactory;


    @Autowired
    public RestTemplateBuilder(LarkRestTemplateProperties properties) {
        this.properties = properties;
    }

    /**
     * 构建restTemplate相关参数设置
     *
     * @param
     * @return
     */
    @Bean
    public RestTemplate restTemplate() {
        org.springframework.boot.web.client.RestTemplateBuilder builder = new org.springframework.boot.web.client.RestTemplateBuilder();
        RestTemplate restTemplate = builder
//                .additionalCustomizers(new LarkRestTemplateCustomizer())
//                .errorHandler(new LarkRestErrorHandler())
                .build();
        restTemplate.setRequestFactory(clientHttpRequestFactory());
        return restTemplate;
    }

    /**
     * 客户端请求链接策略
     *
     * @return
     */
    @Bean
    public ClientHttpRequestFactory clientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setHttpClient(httpClientBuilder().build());
        clientHttpRequestFactory.setConnectTimeout(properties.getConnectTimeout());
        clientHttpRequestFactory.setReadTimeout(properties.getReadTimeout());
        clientHttpRequestFactory.setConnectionRequestTimeout(properties.getConnectionRequestTimeout());
        return clientHttpRequestFactory;
    }

    /**
     * 设置HTTP连接管理器,连接池相关配置管理
     *
     * @return 客户端链接管理器
     */
    @Bean
    public HttpClientBuilder httpClientBuilder() {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
//        httpClientBuilder.setSSLSocketFactory(layeredConnectionSocketFactory);
//        if (!(StringUtils.isEmpty(properties.getKeyStorePath()) && StringUtils.isEmpty(properties.getKeyPassWord()))) {
//
//        }
        if (properties.isEnableProxy()) {
            HttpHost httpHost = new HttpHost(properties.getProxyUrl(), properties.getProxyPort(), properties.getScheme());
            httpClientBuilder.setProxy(httpHost);
        }
        httpClientBuilder.setConnectionManager(poolingConnectionManager());
        return httpClientBuilder;
    }


    /**
     * 链接线程池管理,可以keep-alive不断开链接请求,这样速度会更快 MaxTotal 连接池最大连接数 DefaultMaxPerRoute
     * ValidateAfterInactivity可用空闲连接过期时间,重用空闲连接时会先检查是否空闲时间超过这个时间，如果超过，释放socket重新建立
     * MaxtTotal=400 DefaultMaxPerRoute=200
     * 只连接到http://www.abc.com时，到这个主机的并发最多只有200；而不是400；
     * 而连接到http://www.bac.com 和
     * http://www.ccd.com时，到每个主机的并发最多只有200；即加起来是400（但不能超过400）；所以起作用的设置是DefaultMaxPerRoute
     *
     * @return
     */
    @Bean
    public HttpClientConnectionManager poolingConnectionManager() {
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", layeredConnectionSocketFactory)
                .build();
        PoolingHttpClientConnectionManager poolingConnectionManager = new PoolingHttpClientConnectionManager(registry);
        poolingConnectionManager.setMaxTotal(properties.getMaxTotal());
        poolingConnectionManager.setDefaultMaxPerRoute(properties.getDefaultMaxPerRoute());
        poolingConnectionManager.setValidateAfterInactivity(properties.getValidateAfterInactivity());
        return poolingConnectionManager;
    }
}
