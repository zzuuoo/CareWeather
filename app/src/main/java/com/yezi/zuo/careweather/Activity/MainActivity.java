package com.yezi.zuo.careweather.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yezi.zuo.careweather.R;
import com.yezi.zuo.careweather.Tools.ActivityCollector;
import com.yezi.zuo.careweather.Tools.JSONUtils;
import com.yezi.zuo.careweather.Tools.LogUtil;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.RunnableFuture;

/**
 * Created by zuo on 2016/11/27.
 */

public class MainActivity extends BaseActivity implements View.OnClickListener {


    /**
     * flag 1 白天模式
     * 2 黑夜模式
     */

    public static Weather weather=null;
    public static place Place=null;
    public static int flag = 1;
    public static Context context;

    public static String u = "http://api.map.baidu.com/telematics/v3/weather?location=";
    public static String r = "&output=json&ak=Gi27P5bmIinr86htrjU4ESnY";
    public static String p="西安";
    public static String uri = u +p+r;

    public static ImageView pic;


    public static TextView textPlace, textTime, textTemp,
            textWeather, textpublish,pm25,weather_todaytime;


    public static LinearLayout relativeLayout;
    public static GridView xinqi,weather_do_g;


    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        public void run() {
            this.update();
            handler.postDelayed(this, 1000*60*60*12);
        }

        void update() {
            setTime();

            //刷新msg的内容
        }
    };




    private  Handler weatherhandel = new Handler(){
        public void handleMessage(Message message){
            switch (message.what){
                case 1:
                    String response = (String )message.obj;
                    parseJSONWithJSONObject(response,zifu.city);
                    initData();
                    break;
                case 2:
//                    Toast.makeText(getApplicationContext(),message.obj.toString(),Toast.LENGTH_SHORT).show();
                    pic.setImageBitmap((Bitmap)message.obj);
                    break;
            }
        }
    };
    private  void sendQequestWinthHttpURLConnection(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(uri);
                    connection=(HttpURLConnection)url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();

                    BufferedReader reader =new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while((line=reader.readLine())!=null){
                        response.append(line);
                    }
                    Message message = new Message();
                    message.what=1;
                    message.obj=response.toString();
                    weatherhandel.sendMessage(message);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }




    private  void parseJSONWithJSONObject(String jsonData,String placeData){
        Gson gson =new Gson();
        weather = gson.fromJson(jsonData,Weather.class);
        Place = gson.fromJson(placeData,place.class);
        new Thread(new Runnable() {
            @Override
            public void run() {
               if(weather!=null){
                   Bitmap bitmapday = getImageBitmap(weather.getResults().get(0).
                           getWeather_data().get(0).getDayPictureUrl());
//                   Bitmap bitmapnight = getImageBitmap(weather.getResults().get(0).
//                           getWeather_data().get(0).getNightPictureUrl());
                   Message message = new Message();
                   message.what=1;
                   message.obj=bitmapday;
                   message.what=2;
                   weatherhandel.sendMessage(message);
               }
            }
        }).start();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_a);
        context=getApplicationContext();
        init();
        sendQequestWinthHttpURLConnection();
        handler.postDelayed(runnable, 1000);
        setTime();

        xinqi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Weather_data weather_data = weather.getResults().get(0).getWeather_data().get(position);
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle(weather_data.getDate());
                dialog.setMessage("天气："+weather_data.getWeather()+"    温度："+
                        weather_data.getTemperature()+"    风："
                        +weather_data.getWind());
                dialog.setCancelable(true);
                dialog.show();
//                Toast.makeText(getApplicationContext(),weather_data.getWeather()+weather_data.getTemperature()
//                        +weather_data.getWind(),Toast.LENGTH_SHORT).show();
            }
        });
        weather_do_g.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle(weather.getResults().get(0).getIndex().get(position).getTitle());
                dialog.setMessage(weather.getResults().get(0).
                        getIndex().get(position).getDes());
                dialog.setCancelable(true);
                dialog.show();
//                Toast.makeText(getApplicationContext(),weather.getResults().get(0).
//                        getIndex().get(position).getDes(),Toast.LENGTH_SHORT).show();

            }
        });
//        initData();
    }


    @Override
    protected void onDestroy() {
        handler.removeCallbacks(runnable);
        super.onDestroy();
    }

    public  void initData(){

        if(weather!=null&&weather.getStatus().endsWith("success")){
            textPlace.setText(weather.getResults().get(0).getCurrentCity()+" ✔");
            textTemp.setText(
                    weather.getResults().get(0).getWeather_data().get(0).getTemperature()+
            "  风:"+weather.getResults().get(0).getWeather_data().get(0).getWind());
            pm25.setText(" pm :"+ weather.getResults().get(0).getPm25());
            textTime.setText(weather.getDate());
            textWeather.setText(weather.getResults().get(0).getWeather_data().get(0).getWeather());

            weather_todaytime.setText(weather.getResults().get(0).getWeather_data().get(0).getDate());
            Weather_data_adapter weather_data_adapter =new Weather_data_adapter(MainActivity.this,
                    R.layout.weatherdataitem,weather.getResults().get(0).getWeather_data());
            xinqi = (GridView)findViewById(R.id.weather_day);
            xinqi.setAdapter(weather_data_adapter);


            weather_do_adapter weather_do  =new weather_do_adapter(MainActivity.this,
                    R.layout.weather_doitem,weather.getResults().get(0).getIndex());
            weather_do_g.setAdapter(weather_do);

//            weather_icon.setImageBitmap(
//                    getImageBitmap(weather.getResults().get(0).getWeather_data()
//                            .get(0).getNightPictureUrl()));


        }else{
            Toast.makeText(context,"抱歉，没找到该城市数据",Toast.LENGTH_SHORT).show();
        }
          }

    public void init() {
        pic=(ImageView)findViewById(R.id.picday);
        pm25=(TextView)findViewById(R.id.pm);
        textPlace = (TextView) findViewById(R.id.title_text);
        textTime = (TextView) findViewById(R.id.current_date);
        textTemp = (TextView) findViewById(R.id.temp);
        textWeather = (TextView) findViewById(R.id.weather);
        textpublish = (TextView) findViewById(R.id.publish_text);
        relativeLayout = (LinearLayout) findViewById(R.id.activity_a);
        xinqi = (GridView)findViewById(R.id.weather_day);
        textPlace.setOnClickListener(this);
        weather_do_g=(GridView)findViewById(R.id.weather_do);
        weather_todaytime=(TextView)findViewById(R.id.weather_todaytime);


    }

    public static Bitmap getImageBitmap(String url){
        URL imgUrl = null;
        Bitmap bitmap = null;
        try {
            imgUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection)imgUrl.openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
        return bitmap;
    }

    public  void setTime() {
        setnowTime();
        weatherhandel.sendMessage(new Message());
        sendQequestWinthHttpURLConnection();
        initData();
    }
    public  static void setnowTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        // new Date()为获取当前系统时间
        textTime.setText(df.format(new Date()));
        SimpleDateFormat df2 = new SimpleDateFormat("HH:mm:ss");//设置日期格式
        textpublish.setText(df2.format(new Date()));
//        if(Place!=null){
//            zifu.cityArrayList.clear();
//            zifu.cityArrayList.addAll(Place.getCity().get(0).getA());
//            zifu.cityArrayList.addAll(Place.getCity().get(0).getB());
//            zifu.cityArrayList.addAll(Place.getCity().get(0).getC());
//            zifu.cityArrayList.addAll(Place.getCity().get(0).getD());
//            zifu.cityArrayList.addAll(Place.getCity().get(0).getE());
//            zifu.cityArrayList.addAll(Place.getCity().get(0).getF());
//            zifu.cityArrayList.addAll(Place.getCity().get(0).getG());
//            zifu.cityArrayList.addAll(Place.getCity().get(0).getH());
//            zifu.cityArrayList.addAll(Place.getCity().get(0).getI());
//            zifu.cityArrayList.addAll(Place.getCity().get(0).getJ());
//            zifu.cityArrayList.addAll(Place.getCity().get(0).getK());
//            zifu.cityArrayList.addAll(Place.getCity().get(0).getL());
//            zifu.cityArrayList.addAll(Place.getCity().get(0).getM());
//            zifu.cityArrayList.addAll(Place.getCity().get(0).getN());
//            zifu.cityArrayList.addAll(Place.getCity().get(0).getO());
//            zifu.cityArrayList.addAll(Place.getCity().get(0).getP());
//            zifu.cityArrayList.addAll(Place.getCity().get(0).getQ());
//            zifu.cityArrayList.addAll(Place.getCity().get(0).getR());
//            zifu.cityArrayList.addAll(Place.getCity().get(0).getS());
//            zifu.cityArrayList.addAll(Place.getCity().get(0).getT());
//            zifu.cityArrayList.addAll(Place.getCity().get(0).getU());
//            zifu.cityArrayList.addAll(Place.getCity().get(0).getV());
//            zifu.cityArrayList.addAll(Place.getCity().get(0).getW());
//            zifu.cityArrayList.addAll(Place.getCity().get(0).getX());
//            zifu.cityArrayList.addAll(Place.getCity().get(0).getY());
//            zifu.cityArrayList.addAll(Place.getCity().get(0).getZ());
//        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.shezhi:
                Toast.makeText(getApplicationContext(),"你想干嘛？",Toast.LENGTH_SHORT).show();
//                if(Place!=null)
//                    Toast.makeText(getApplicationContext(), Place.getCity().get(0).热门.get(0).getName(), Toast.LENGTH_SHORT).show();
//                if(weather!=null&&weather.getStatus().equals("success")){
//                    Weather_data_adapter weather_data_adapter =new Weather_data_adapter(MainActivity.this,
//                            R.layout.weatherdataitem,weather.getResults().get(0).getWeather_data());
//                    GridView gridView = (GridView)findViewById(R.id.weather_day);
//                    gridView.setAdapter(weather_data_adapter);
//                }
//                Toast.makeText(getApplicationContext(), weather.getDate()+weather.getResults().get(0).getCurrentCity(), Toast.LENGTH_SHORT).show();
//
//                textPlace.setText(weather.getResults().get(0).getCurrentCity());
//                textTemp.setText(weather.getResults().get(0).getWeather_data().get(0).getTemperature());
                break;
            case R.id.quit:
                ActivityCollector.quitApp();
                break;
            default:
                break;
        }

        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_text:

                Intent intent = new Intent(MainActivity.this,choice_city.class);
                startActivityForResult(intent,1);
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        switch (resultCode){
            case 1:
                p=data.getStringExtra("pl");
                if(!p.equals("")){
                    uri=u+p+r;
                    sendQequestWinthHttpURLConnection();
                    setTime();
                }else{

                }
                break;
            default:
                break;
        }
    }
}
