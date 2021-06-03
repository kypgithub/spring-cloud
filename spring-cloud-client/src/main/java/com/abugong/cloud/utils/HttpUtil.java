package com.abugong.cloud.utils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpUtil {
	
    static String uri = "http://127.0.0.1:8080/simpleweb";
    public static String get(String url, List<BasicNameValuePair> list) {
        try {
            CloseableHttpClient client = null;
            CloseableHttpResponse response = null;
            try {
            	//1: 创建httpClient
                client = HttpClients.createDefault();
                //2: 转化参数
                if (!list.isEmpty()) {
                	String params = EntityUtils.toString(new UrlEncodedFormEntity(list, Consts.UTF_8));
                	url = url + "?" + params;
                }
            	//3: 创建httpGet请求
                HttpGet httpGet = new HttpGet(uri + url);
                response = client.execute(httpGet);
                //4: 获取实体
                HttpEntity entity = response.getEntity();
                String result = EntityUtils.toString(entity);
        		return result;
            } finally {
                if (response != null) {
                    response.close();
                }
                if (client != null) {
                    client.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "request fail";
    }

    public static String post(String url, List<BasicNameValuePair> parames) {
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {
            HttpPost httpPost = new HttpPost(uri + url);
            httpPost.setEntity(new UrlEncodedFormEntity(parames, "UTF-8"));
            client = HttpClients.createDefault();
            response = client.execute(httpPost);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (response != null) {
                try {
					response.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            if (client != null) {
                try {
					client.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        }
		return "request fail";
    }

    public static String postJson(String url, Map<String, Object>  data) {
        try {
            CloseableHttpClient client = null;
            CloseableHttpResponse response = null;
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                HttpPost httpPost = new HttpPost(uri + url);
                httpPost.setHeader(HTTP.CONTENT_TYPE, "application/json");
                httpPost.setEntity(new StringEntity(objectMapper.writeValueAsString(data),
                        ContentType.create("text/json", "UTF-8")));

                client = HttpClients.createDefault();
                response = client.execute(httpPost);
                HttpEntity entity = response.getEntity();
                String result = EntityUtils.toString(entity);
                return result;
            } finally {
                if (response != null) {
                    response.close();
                }
                if (client != null) {
                    client.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		return "request fail";
    }
}