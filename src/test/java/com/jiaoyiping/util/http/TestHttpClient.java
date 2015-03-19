package com.jiaoyiping.util.http;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * Created with Intellij IDEA
 * USER: 焦一平
 * Date: 2015/2/15
 * Time: 13:30
 * To change this template use File | Settings | File Template
 */
public class TestHttpClient {
    HttpGet get = new HttpGet("http://localhost:8080");
    HttpClient client = new DefaultHttpClient();
}
