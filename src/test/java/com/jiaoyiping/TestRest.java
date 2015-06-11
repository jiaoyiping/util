package com.jiaoyiping;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * Created with IntelliJ IDEA.
 * User: 焦一平
 * Date: 2015/6/8
 * Time: 10:56
 * To change this template use File | Settings | File Templates.
 */
public class TestRest {

    public static String get(String url){
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("charSet","UTF8");
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(response.getStatusLine().getStatusCode() == 200){
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                StringBuffer result = new StringBuffer();
                String line;
                while ((line = bufferedReader.readLine())!= null){
                    result.append(line);
                }
                return result.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    @Test
    public void testContract() throws UnsupportedEncodingException {
        String url = "http://127.0.0.1:8280/service/pds/contract/移有限分研究合同%20【2012】0486";
        System.out.println(url);
        System.out.println(get(url));
    }



}
