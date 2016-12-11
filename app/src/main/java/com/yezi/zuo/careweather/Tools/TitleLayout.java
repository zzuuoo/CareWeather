package com.yezi.zuo.careweather.Tools;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatDelegate;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.yezi.zuo.careweather.Activity.MainActivity;
import com.yezi.zuo.careweather.R;

/**
 * Created by zuo on 2016/11/28.
 * 自定义控件布局
 */

public class TitleLayout extends LinearLayout {



    private Handler handler = new Handler(){
        public void handleMessage(Message message){
            switch (message.what){
                case 1:


                    if(MainActivity.flag==1){

                        MainActivity.flag=0;
                        MainActivity.relativeLayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.night));
                    }
                    else{
                        MainActivity.relativeLayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.day));
                        MainActivity.flag=1;
                    }

                    break;
                case 2:
                    MainActivity.setnowTime();
                    break;
                default:
                    break;
            }
        }
    };


    public TitleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title,this);
        Button titlepublish = (Button)findViewById(R.id.title_publish);
        final Button titlemodel = (Button)findViewById(R.id.title_model);
        titlepublish.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message = new Message();
                        message.what=2;
                        handler.sendMessage(message);
                    }
                }).start();
            }
        });
        titlemodel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                Message message = new Message();
                                message.what=1;
                                handler.sendMessage(message);
                            }
                        }).start();
//                Toast.makeText(getContext(),"异步消息处理",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
