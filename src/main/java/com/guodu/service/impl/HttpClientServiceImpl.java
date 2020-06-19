package com.guodu.service.impl;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class HttpClientServiceImpl {
    Logger logger = LoggerFactory.getLogger(HttpClientServiceImpl.class);

    public String doGet(String url, Map<String, String> params) {
        String result = "-1";
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = null;
        try {
            URIBuilder uriBuilder = new URIBuilder(url);
            if (null != params && !params.isEmpty()) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    uriBuilder.addParameter(entry.getKey(), entry.getValue());
                }
            }
            URI uri = uriBuilder.build();
            httpGet = new HttpGet(uri);
            HttpResponse response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {// 返回200，请求成功
                result = EntityUtils.toString(response.getEntity());
                logger.info("请求成功！，返回数据：" + result);
            } else {
                logger.info("请求失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("请求失败!");
        } finally {
            if (null != httpGet) {
                httpGet.releaseConnection();
            }
        }
        return result;
    }

    /**
     * \* @Title: doPost
     * \* @Description: post请求
     * \* @param url
     * \* @param params
     * \* @return
     * \* @author staystand
     */
    public String doPost(String url, Map<String, String> params) {
        String result = "-1";
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(url);
        try { // 参数键值对
            if (null != params && !params.isEmpty()) {
                List<NameValuePair> pairs = new ArrayList<NameValuePair>();
                NameValuePair pair = null;
                for (String key : params.keySet()) {
                    pair = new BasicNameValuePair(key, params.get(key));
                    pairs.add(pair);
                }
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(pairs);
                httpPost.setEntity(entity);
            }
            HttpResponse response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result = EntityUtils.toString(response.getEntity(), "utf-8");
                logger.info("返回数据：>>>" + result);
            } else {
                logger.info("请求失败！，url:" + url);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("请求失败");
            e.printStackTrace();
        } finally {
            if (null != httpPost) {
                httpPost.releaseConnection();
            }
        }
        return result;
    }
}
