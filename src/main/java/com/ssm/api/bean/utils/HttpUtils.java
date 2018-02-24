package com.ssm.api.bean.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpUtils {

    public static final String GET = "get";
    public static final String POST = "post";
    public static final String PUT = "put";

    public static Result get(String url){
        HttpGet httpGet = new HttpGet(url);
        return send(httpGet);
    }
    public static Result get(String url,Map<String, String> header){
        HttpGet httpGet = new HttpGet(url);
        for(Map.Entry<String, String> entry : header.entrySet()) {
        	httpGet.setHeader(entry.getKey(), entry.getValue());
        }
        return send(httpGet);
    }

    /**
     * 
     * @param url 请求链接
     * @param header 请求头信息
     * @param jsonParam	请求参数
     * @return
     */
    public static Result post(String url, Map<String, String> header, JSONObject jsonParam) {
        HttpPost httpPost = new HttpPost(url);
        try {
        	String e = jsonParam.toString();
            StringEntity entity = new StringEntity(e,"utf-8");
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");
            httpPost.setEntity(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(Map.Entry<String, String> entry : header.entrySet()) {
            httpPost.setHeader(entry.getKey(), entry.getValue());
        }
        return send(httpPost);
    }

    public static Result post(String url, Map<String, String> params){
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> nvps = buildNameValuePairList(params);
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps,"utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return send(httpPost);
    }

    public static Result post(String url, String param){
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");
        try {
            httpPost.setEntity(new StringEntity(param, "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return send(httpPost);
    }

    public static Result put(String url, Map<String, String> params){
        HttpPut httpPut = new HttpPut(url);
        List<NameValuePair> nvps = buildNameValuePairList(params);
        try {
            httpPut.setEntity(new UrlEncodedFormEntity(nvps,"utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return send(httpPut);
    }

    public static Result send(HttpRequestBase httpRequest){
        Result result = null;
        CloseableHttpClient httpclient = HttpClients.createDefault();

        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpRequest);
            result = buildResult(response);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpRequest.abort();

            try {
                if(response != null){
                    response.close();
                }

                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public static Result send(String url, Map<String, String> params, String httpMethod){
        HttpUtils.Result result = null;
        switch (httpMethod) {
            case HttpUtils.GET:
                result = HttpUtils.get(url);
                break;
            case HttpUtils.POST:
                result = HttpUtils.post(url, params);
                break;
            case HttpUtils.PUT:
                result = HttpUtils.put(url, params);
                break;
        }

        return result;
    }

    public static List<NameValuePair> buildNameValuePairList(Map<String, String> params){
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }

        return nvps;
    }

    public static Result buildResult(HttpResponse response){
        Result result = new Result();
        int statusCode = response.getStatusLine().getStatusCode();
        result.setStatusCode(statusCode);

        if(statusCode == 200){
            try {
                HttpEntity httpentity = response.getEntity();
                result.setContent(EntityUtils.toString(httpentity,"utf-8"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    /**
     * 获取请求的HTTP Body的内容
     */
    public static String getRequestBody(HttpServletRequest request){
        String bodyContent = null;
        try {
            BufferedReader br = request.getReader();
            bodyContent = getRequestBody(br);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bodyContent;
    }

    public static String getRequestBody(BufferedReader br){
        StringBuilder bodyContent = new StringBuilder();
        try {
            String temp;
            while((temp = br.readLine()) != null){
                bodyContent.append(temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bodyContent.toString();
    }


    public static class Result{
        private int statusCode;

        private String content;

        public JSONObject toJSONObject(){
            return JSON.parseObject(this.content);
        }

        public JSONArray toJSONArray(){
            return JSON.parseArray(this.content);
        }

        public int getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(int statusCode) {
            this.statusCode = statusCode;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
