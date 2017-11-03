package cn.ipaynow.util.httpkit;

import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HttpContext;

import javax.net.ssl.SSLContext;
import java.io.IOException;

public class HttpTookit extends BaseHttpClient {

    public HttpTookit() {
        RequestConfig config = RequestConfig.custom().setConnectTimeout(60000).setSocketTimeout(60000).build();

        SSLContext sslContext = null;
        try {
            sslContext = SSLContexts.custom()
                    .useTLS()
                    .build();
        } catch (Exception e) {

        }
        if (sslContext != null) {
            httpClient = HttpClientBuilder.create().setSslcontext(sslContext).setDefaultRequestConfig(config).build();
        } else {
            httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
        }
    }

    public HttpTookit(Integer connectTimeout, Integer socketTimeout) {
        RequestConfig config = RequestConfig.custom().setConnectTimeout(connectTimeout).setSocketTimeout(socketTimeout).build();
        httpClient = HttpClientBuilder.create().setRetryHandler(new HttpRequestRetryHandler() {
            @Override
            public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
                return true; //超时设置生效配置（设置重试选项）
            }
        }).setDefaultRequestConfig(config).build();
    }
}