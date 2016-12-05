package com.yezi.zuo.careweather.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

public class MainActivity extends BaseActivity {


    /**
     * flag 1 白天模式
     * 2 黑夜模式
     */

//    String aaa="{\"error\":0,\"status\":\"success\",\"date\":\"2016-12-05\",\"results\":[{\"currentCity\":\"西安\",\"pm25\":\"94\",\"index\":[{\"title\":\"穿衣\",\"zs\":\"较冷\",\"tipt\":\"穿衣指数\",\"des\":\"建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。\"},{\"title\":\"洗车\",\"zs\":\"较适宜\",\"tipt\":\"洗车指数\",\"des\":\"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。\"},{\"title\":\"旅游\",\"zs\":\"适宜\",\"tipt\":\"旅游指数\",\"des\":\"天气较好，温度适宜，是个好天气哦。这样的天气适宜旅游，您可以尽情地享受大自然的风光。\"},{\"title\":\"感冒\",\"zs\":\"易发\",\"tipt\":\"感冒指数\",\"des\":\"昼夜温差很大，易发生感冒，请注意适当增减衣服，加强自我防护避免感冒。\"},{\"title\":\"运动\",\"zs\":\"较不宜\",\"tipt\":\"运动指数\",\"des\":\"天气较好，但考虑天气寒冷，推荐您进行各种室内运动，若在户外运动请注意保暖并做好准备活动。\"},{\"title\":\"紫外线强度\",\"zs\":\"中等\",\"tipt\":\"紫外线强度指数\",\"des\":\"属中等强度紫外线辐射天气，外出时建议涂擦SPF高于15、PA+的防晒护肤品，戴帽子、太阳镜。\"}],\"weather_data\":[{\"date\":\"周一 12月05日 (实时：13℃)\",\"dayPictureUrl\":\"http://api.map.baidu.com/images/weather/day/qing.png\",\"nightPictureUrl\":\"http://api.map.baidu.com/images/weather/night/qing.png\",\"weather\":\"晴\",\"wind\":\"东北风微风\",\"temperature\":\"13 ~ 0℃\"},{\"date\":\"周二\",\"dayPictureUrl\":\"http://api.map.baidu.com/images/weather/day/qing.png\",\"nightPictureUrl\":\"http://api.map.baidu.com/images/weather/night/qing.png\",\"weather\":\"晴\",\"wind\":\"东北风微风\",\"temperature\":\"12 ~ -1℃\"},{\"date\":\"周三\",\"dayPictureUrl\":\"http://api.map.baidu.com/images/weather/day/duoyun.png\",\"nightPictureUrl\":\"http://api.map.baidu.com/images/weather/night/qing.png\",\"weather\":\"多云转晴\",\"wind\":\"东北风微风\",\"temperature\":\"9 ~ 1℃\"},{\"date\":\"周四\",\"dayPictureUrl\":\"http://api.map.baidu.com/images/weather/day/duoyun.png\",\"nightPictureUrl\":\"http://api.map.baidu.com/images/weather/night/duoyun.png\",\"weather\":\"多云\",\"wind\":\"东北风微风\",\"temperature\":\"12 ~ 2℃\"}]}]}";
    public static Weather weather=null;
    public static int flag = 1;

    public static int model = 1;
    /**
     * model 1 晴天
     * 2 雨天
     * 3 雪天
     * 4 雷天
     */

    public static String u = "http://api.map.baidu.com/telematics/v3/weather?location=";
    public static String r = "&output=json&ak=Gi27P5bmIinr86htrjU4ESnY";
    public static String uri = u +"西安" +r;


    public static TextView textPlace, textTime, textTemp, textWeather, textpublish;


    public static LinearLayout relativeLayout;


    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        public void run() {
            this.update();
            handler.postDelayed(this, 1000 * 120);// 间隔120秒
        }

        void update() {
            setTime();
            //刷新msg的内容
        }
    };
    private Handler weatherhandel = new Handler(){
        public void handleMessage(Message message){
            switch (message.what){
                case 1:
                    String response = (String )message.obj;
                    parseJSONWithJSONObject(response);
            }
        }
    };
    private void sendQequestWinthHttpURLConnection(){
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




    private void parseJSONWithJSONObject(String jsonData){
        Gson gson =new Gson();
        weather = gson.fromJson(jsonData,Weather.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_a);
        init();
        setTime();
        handler.postDelayed(runnable, 1000 * 60);
        sendQequestWinthHttpURLConnection();
        initData();
    }


    @Override
    protected void onDestroy() {
        handler.removeCallbacks(runnable);
        super.onDestroy();
    }

    private static void initData(){
        if(weather!=null){
            textPlace.setText(weather.getResults().get(0).getCurrentCity());
            textTemp.setText(
                    weather.getResults().get(0).getWeather_data().get(0).getTemperature());
            textTime.setText(weather.getDate());
            textWeather.setText(weather.getResults().get(0).getWeather_data().get(0).getWeather());

        }
          }

    public void init() {
        textPlace = (TextView) findViewById(R.id.title_text);
        textTime = (TextView) findViewById(R.id.current_date);
        textTemp = (TextView) findViewById(R.id.temp);
        textWeather = (TextView) findViewById(R.id.weather);
        textpublish = (TextView) findViewById(R.id.publish_text);
        relativeLayout = (LinearLayout) findViewById(R.id.activity_a);


    }


    public static void setTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        // new Date()为获取当前系统时间
        textTime.setText(df.format(new Date()));
        SimpleDateFormat df2 = new SimpleDateFormat("HH:mm:ss");//设置日期格式
        textpublish.setText("今天" + df2.format(new Date()) + "发布");

        initData();

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
                if(weather!=null)
                Toast.makeText(getApplicationContext(), weather.getDate()+weather.getResults().get(0).getCurrentCity(), Toast.LENGTH_SHORT).show();
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

}