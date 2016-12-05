package com.yezi.zuo.careweather.Tools;

/**
 * Created by zuo on 2016/12/5.
 */

import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class JSONUtils {

    public JSONUtils (){}
    /**
     * 通过url 向服务器发送请求，服务器返回json数据
     * @param url
     *          url地址
     * @return
     *          string类型的json格式数据
     */
    public static String getJSON(String url){
        StringBuilder builder=new StringBuilder();
        HttpGet httpRequest=new HttpGet(url);
        try {
            HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequest);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent(),
                        "UTF-8"));
                for (String s = reader.readLine(); s != null; s = reader.readLine()) {
                    builder.append(s);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}