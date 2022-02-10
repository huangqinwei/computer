package com.huang.http_request.service.impl;

import com.huang.core.PubUtils;
import com.huang.http_request.service.HttpRequestService;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Service
public class HttpRequestServiceImpl implements HttpRequestService {
    /**
     * GET请求
     * @param url
     * @return
     */
    @Override
    public String httpGet(String url) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        String result = null;// 转成string
        try {
            response = httpclient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, "UTF-8");
            EntityUtils.consume(entity);   // 释放stream
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * POST请求
     * @param url
     * @return
     */
    @Override
    public String httpPost(String url, Object object) {
        HttpPost request = new HttpPost(url);
        request.addHeader("Content-Type", "application/json");
        //request.addHeader(new BasicHeader("cookie" , "JSESSIONID=9790E72B512A88EDD43632AE70DD3FCC"));
        String body = JSONObject.toJSONString(object);
        request.setEntity(new StringEntity(body, ContentType.create("text/json", "UTF-8")));
        String result = null;
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            CloseableHttpResponse response = null;
            response = httpclient.execute(request);
            HttpEntity httpEntity = response.getEntity();
            if (PubUtils.isNotEmpty(httpEntity)) {
                result = EntityUtils.toString(httpEntity, "UTF-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
