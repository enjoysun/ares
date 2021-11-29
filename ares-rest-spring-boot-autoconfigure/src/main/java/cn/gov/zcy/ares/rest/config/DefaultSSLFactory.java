package cn.gov.zcy.ares.rest.config;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-11-29 19:33:46
 */
public class DefaultSSLFactory {
    @Bean
    @ConditionalOnMissingBean
    public LayeredConnectionSocketFactory layeredConnectionSocketFactory(LarkRestTemplateProperties properties) throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException, UnrecoverableKeyException, KeyManagementException {
        if (!(StringUtils.isEmpty(properties.getKeyStorePath()) && StringUtils.isEmpty(properties.getKeyPassWord()))) {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(new ClassPathResource(properties.getKeyStorePath()).getInputStream(), properties.getKeyPassWord().toCharArray());
            return new SSLConnectionSocketFactory(
                    new SSLContextBuilder()
                            //检查客户端证书信任
                            .loadTrustMaterial(null, new TrustSelfSignedStrategy())
                            //校验密钥
                            .loadKeyMaterial(keyStore, properties.getKeyPassWord().toCharArray())
                            .build(),
                    NoopHostnameVerifier.INSTANCE);
        }

        // skip ssl 验证
        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

        SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
                .loadTrustMaterial(null, acceptingTrustStrategy)
                .build();
        return new SSLConnectionSocketFactory(sslContext, new NoopHostnameVerifier());
    }
}
