package cn.ipaynow.util.httpkit;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.impl.client.HttpClients;

import javax.net.ssl.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class HttpsTookit extends BaseHttpClient{

    static {
        //开启sslv3
        Security.setProperty("jdk.tls.disabledAlgorithms","");
    }
    public static String getRequest(String url,int timeOut, boolean ignoreSsl) throws Exception{
        URL u = new URL(url);
        if("https".equalsIgnoreCase(u.getProtocol()) && ignoreSsl){
            ignoreSsl();
        }
        URLConnection conn = u.openConnection();
        conn.setConnectTimeout(timeOut);
        conn.setReadTimeout(timeOut);
        return IOUtils.toString(conn.getInputStream());
    }
    public static String postRequest(String urlAddress,String args,int timeOut, String charset, boolean ignoreSsl) throws Exception{
        URL url = new URL(urlAddress);
        if("https".equalsIgnoreCase(url.getProtocol()) && ignoreSsl){
            ignoreSsl();
        }
        URLConnection u = url.openConnection();
        u.setDoInput(true);
        u.setDoOutput(true);
        u.setConnectTimeout(timeOut);
        u.setReadTimeout(timeOut);
        OutputStreamWriter osw = new OutputStreamWriter(u.getOutputStream(), charset);
        osw.write(args);
        osw.flush();
        osw.close();
        u.getOutputStream();
        return IOUtils.toString(u.getInputStream(), charset);
    }

    /**
     * 忽略证书报错
     * @throws Exception
     */
    public static void ignoreSsl() throws Exception{
        HostnameVerifier hv = new HostnameVerifier() {
            public boolean verify(String urlHostName, SSLSession session) {
                return true;
            }
        };
        trustAllHttpsCertificates();
        HttpsURLConnection.setDefaultHostnameVerifier(hv);
    }

    private static void trustAllHttpsCertificates() throws Exception {
        TrustManager[] trustAllCerts = new TrustManager[1];
        TrustManager tm = new miTM();
        trustAllCerts[0] = tm;
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, null);
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    }
    static class miTM implements TrustManager,X509TrustManager {
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
        public boolean isServerTrusted(X509Certificate[] certs) {
            return true;
        }
        public boolean isClientTrusted(X509Certificate[] certs) {
            return true;
        }
        public void checkServerTrusted(X509Certificate[] certs, String authType)
                throws CertificateException {
            return;
        }
        public void checkClientTrusted(X509Certificate[] certs, String authType)
                throws CertificateException {
            return;
        }
    }


    public HttpsTookit(String pkcs12FileName,String pass) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, KeyManagementException, UnrecoverableKeyException{
         RequestConfig config = RequestConfig.custom().setConnectTimeout(60000).setSocketTimeout(15000).build();

         KeyStore keyStore  = KeyStore.getInstance("PKCS12");
         if(pkcs12FileName == null){
             keyStore.load(null,null);
         }else{
             FileInputStream instream = new FileInputStream(new File(pkcs12FileName));
             try {
                 keyStore.load(instream, pass.toCharArray());
             } finally {
                 instream.close();
             }
         }
         // Trust own CA and all self-signed certs
        SSLContext sslcontext = null;
        if(pass != null){
            sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, pass.toCharArray()).build();
        }else{
            sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, null).build();
        }

         // Allow TLSv1 protocol only
         SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                 sslcontext,
                 new String[] {"TLSv1.1"},//SSLv3 TLSv1 TLSv1.1 TLSv1.2 TLSv1.3;    TLSv1即TLSv1.0,如果该参数传null则默认使用
//                 java1.7 开启SSLv3需要打开java.security中的“jdk.tls.disabledAlgorithms=SSLv3
//                 nginx升级到1.13.0才能启用TLSv1.3
                 null,
                 SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
         httpClient = HttpClients.custom().setDefaultRequestConfig(config).setSSLSocketFactory(sslsf).build();
    }
 
    
    
    //service nginx restart
    //nginx -t
    
    public static void main(String [] args) throws Exception {
        String pkcs12FileName = "D:/33iq_nopass.key";
        String pass = "";
//        String s = new HttpsTookit(pkcs12FileName,pass).doGet("https://api.mch.weixin.qq.com/secapi/pay/refund", null);
        String s = new HttpsTookit(null,null).doGet("https://nginx-test.ipaynow.cn/test.html", null);
        System.out.println(s);
    }
}