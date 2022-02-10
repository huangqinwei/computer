package com.huang.http_request.service;

public interface HttpRequestService {
    /**
     * GET请求
     * @param url
     * @return
     */
    public String httpGet(String url);

    /**
     * POST请求
     * @param url
     * @return
     */
    public String httpPost(String url,Object obj);
}
