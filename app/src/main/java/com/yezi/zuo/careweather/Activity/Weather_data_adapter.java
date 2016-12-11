package com.yezi.zuo.careweather.Activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.yezi.zuo.careweather.R;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by zuo on 2016/12/7.
 */

public class Weather_data_adapter extends ArrayAdapter<Weather_data>{
    private int resourceId;

    public Weather_data_adapter(Context context, int resource, List<Weather_data> objects) {
        super(context, resource, objects);
        resourceId=resource;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Weather_data weather_data = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,null);

        TextView day = (TextView)view.findViewById(R.id.day);
        ImageView pic = (ImageView)view.findViewById(R.id.pic);
        TextView wea = (TextView)view.findViewById(R.id.weather_weather);
        day.setText(weather_data.getDate().substring(0,2));
//        Bitmap bitmap = MainActivity.getImageBitmap(weather_data.getDayPictureUrl());
//        pic.setImageBitmap(bitmap);
        wea.setText(weather_data.getWeather());
        return view;
    }

    /**
     * 获取图片
     * @param path 图片路径
     * @return
     */
    public static Bitmap getImageBitmap(String path) throws Exception{
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestMethod("GET");
        if(conn.getResponseCode() == 200){
            InputStream inStream = conn.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(inStream);
            return bitmap;
        }
        return null;
    }
//    public static Bitmap getHttpBitmap(String url) {
//        URL myFileUrl = null;
//        Bitmap bitmap = null;
//        try {
//            myFileUrl = new URL(url);
//            } catch (MalformedURLException e) {
//            e.printStackTrace();
//            }
//        try {
//            HttpURLConnection conn = (HttpURLConnection) myFileUrl
//            .openConnection();
//            conn.setConnectTimeout(0);
//            conn.setDoInput(true);
//            conn.connect();
//            InputStream is = conn.getInputStream();
//            bitmap = BitmapFactory.decodeStream(is);
//            is.close();
//            } catch (IOException e) {
//            e.printStackTrace();
//            }
//        return bitmap;
//        }
}
