package cn.ipaynow.util.httpkit;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class BaseHttpClient {

    protected  CloseableHttpClient httpClient;
    protected  final String CHARSET = "UTF-8";
    
    
    
    
    public String doGet(String url) throws ParseException, UnsupportedEncodingException, IOException{
        return doGet(url,null);
    }
    public String doGet(String url, Map<String, String> params) throws ParseException, UnsupportedEncodingException, IOException{
        return doGet(url, params,CHARSET);
    }
    public String doGet(String url,Map<String,String> params,String charset) throws ParseException, UnsupportedEncodingException, IOException{
        if(StringUtils.isBlank(url)){
            return null;
        }
        if(params != null && !params.isEmpty()){
            List<NameValuePair> pairs = new ArrayList<NameValuePair>(params.size());
            for(Map.Entry<String,String> entry : params.entrySet()){
                String value = entry.getValue();
                if(value != null){
                    pairs.add(new BasicNameValuePair(entry.getKey(),value));
                }
            }
            url += "?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs, charset));
        }
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = httpClient.execute(httpGet);
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode != 200) {
            httpGet.abort();
            throw new RuntimeException("HttpClient,error status code :" + statusCode);
        }
        HttpEntity entity = response.getEntity();
        String result = null;
        if (entity != null){
            result = EntityUtils.toString(entity, charset);
        }
        EntityUtils.consume(entity);
        response.close();
        return result;
    }





    public static class HttpClientException extends Exception{

        public HttpClientException(String errMsg) {
            this.errMsg = errMsg;
        }
        private String errMsg;

        public String getErrMsg() {
            return errMsg;
        }

        public void setErrMsg(String errMsg) {
            this.errMsg = errMsg;
        }
    }

    public String doPost(String url,String postContent,Map<String,String> params,Map<String,String> headers,String charset) throws HttpClientException {
        if(StringUtils.isBlank(url)){
            return null;
        }
        try {
            List<NameValuePair> pairs = null;
            if(params != null && !params.isEmpty()){
                pairs = new ArrayList<NameValuePair>(params.size());
                for(Map.Entry<String,String> entry : params.entrySet()){
                    String value = entry.getValue();
                    if(value != null){
                        pairs.add(new BasicNameValuePair(entry.getKey(),value));
                    }
                }
            }
            HttpPost httpPost = new HttpPost(url);
            if(pairs != null){
                httpPost.setEntity(new UrlEncodedFormEntity(pairs, charset));
            }
            if(!StringUtils.isBlank(postContent)){
                httpPost.setEntity(new StringEntity(postContent, charset));
            }

            if(headers != null){
                for(Map.Entry<String,String> entry : headers.entrySet()){
                    httpPost.setHeader(entry.getKey(),entry.getValue());
                }
            }
            CloseableHttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 302) {
                Header header = response.getFirstHeader("location"); // 跳转的目标地址是在 HTTP-HEAD 中的
                response.close();
                return header.getValue();
            }
            if (statusCode != 200) {
                httpPost.abort();
                throw new HttpClientException("http status code is " + statusCode);
            }

            HttpEntity entity = response.getEntity();
            String result = null;
            if (entity != null){
                result = EntityUtils.toString(entity, charset);
            }
            EntityUtils.consume(entity);
            response.close();
            return result;
        } catch (Exception e) {
            if(e instanceof  HttpClientException) throw (HttpClientException)e;
            e.printStackTrace();
        }
        return null;
    }




    public String doPostUrlEncodeForm(String url,Map<String,String> params,String charset){
        List<NameValuePair> pairs = null;
        if(params != null && !params.isEmpty()){
            pairs = new ArrayList<NameValuePair>(params.size());
            for(Map.Entry<String,String> entry : params.entrySet()){
                if(entry.getValue() != null){
                    pairs.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));
                }
            }
        }
        UrlEncodedFormEntity reqEntity = null;
        try {
            reqEntity = new UrlEncodedFormEntity(pairs,charset);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }


        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(reqEntity);

        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode != 200) {
            httpPost.abort();
            throw new RuntimeException("HttpClient,error status code :" + statusCode);
        }
        HttpEntity entity = response.getEntity();
        String result = null;
        if (entity != null){
            try {
                result = EntityUtils.toString(entity, charset);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        try {
            EntityUtils.consume(entity);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        try {
            response.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }



    
    public void release() throws IOException{
        this.httpClient.close();
    }
}
