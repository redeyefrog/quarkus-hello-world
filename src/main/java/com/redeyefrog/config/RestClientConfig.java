package com.redeyefrog.config;

import com.redeyefrog.rest.client.WireMockClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.ssl.SSLContexts;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.RestClientBuilder;

import javax.inject.Singleton;
import javax.net.ssl.SSLContext;
import java.net.URI;

@Slf4j
public class RestClientConfig {

    @ConfigProperty(name = "base.url")
    String baseUrl;

//    @Named("wireMockClient") // remove application.yml setting to use
    @Singleton
    public WireMockClient wireMockClient() {
        return RestClientBuilder.newBuilder()
                .baseUri(URI.create(baseUrl))
                .hostnameVerifier(NoopHostnameVerifier.INSTANCE)
                .sslContext(getSSLContext())
                .build(WireMockClient.class);
    }

//    @Named("wireMockClientWithoutSSL")
    @Singleton
    public WireMockClient wireMockClientWithoutSSL() {
        return RestClientBuilder.newBuilder()
                .baseUri(URI.create(baseUrl))
                .hostnameVerifier(NoopHostnameVerifier.INSTANCE)
                .build(WireMockClient.class);
    }

    private SSLContext getSSLContext() {
        try {
            return SSLContexts.custom()
                    .loadTrustMaterial(TrustAllStrategy.INSTANCE)
                    .build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

}
