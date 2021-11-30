package cn.gov.zcy.ares.rest.config;

import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-11-29 11:54:46
 */
@ConfigurationProperties(prefix = "lark.rest.template")
public class LarkRestTemplateProperties {
    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

    public int getConnectionRequestTimeout() {
        return connectionRequestTimeout;
    }

    public void setConnectionRequestTimeout(int connectionRequestTimeout) {
        this.connectionRequestTimeout = connectionRequestTimeout;
    }

    public int getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    public int getDefaultMaxPerRoute() {
        return defaultMaxPerRoute;
    }

    public void setDefaultMaxPerRoute(int defaultMaxPerRoute) {
        this.defaultMaxPerRoute = defaultMaxPerRoute;
    }

    public int getValidateAfterInactivity() {
        return validateAfterInactivity;
    }

    public void setValidateAfterInactivity(int validateAfterInactivity) {
        this.validateAfterInactivity = validateAfterInactivity;
    }

    /**
     * 连接超时时间/毫秒
     */
    private int connectTimeout = 10000;

    /**
     * 读写超时时间/毫秒
     */
    private int readTimeout = 6000;

    /**
     * 连接池获取到连接超时时间/毫秒
     */
    private int connectionRequestTimeout = 6000;

    /**
     * 同时最大连接数，即连接池实例数量
     */
    private int maxTotal = 200;

    /**
     * 同一路由最大连接数量
     */
    private int defaultMaxPerRoute = 100;

    /**
     * 连接空闲时间检查可用间隔
     */
    private int validateAfterInactivity = 15000;

    public String getKeyStorePath() {
        return keyStorePath;
    }

    public void setKeyStorePath(String keyStorePath) {
        this.keyStorePath = keyStorePath;
    }

    public String getKeyPassWord() {
        return keyPassWord;
    }

    public void setKeyPassWord(String keyPassWord) {
        this.keyPassWord = keyPassWord;
    }

    /**
     * ssl 客户端jks地址
     * */
    private String keyStorePath;

    /**
     * 密码配置
     * */
    private String keyPassWord;

    public boolean isEnableProxy() {
        return enableProxy;
    }

    public void setEnableProxy(boolean enableProxy) {
        this.enableProxy = enableProxy;
    }

    public String getProxyUrl() {
        return proxyUrl;
    }

    public void setProxyUrl(String proxyUrl) {
        this.proxyUrl = proxyUrl;
    }

    public int getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(int proxyPort) {
        this.proxyPort = proxyPort;
    }

    /**
     * 是否开启代理
     * */
    private boolean enableProxy = false;

    /**
     * 代理地址
     * */
    private String proxyUrl;

    /**
     * 代理端口
     * */
    private int proxyPort;

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    /**
     * 代理scheme: http|https
     * */
    private String scheme;
}
