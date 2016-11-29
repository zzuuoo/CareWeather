package com.yezi.zuo.careweather.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.yezi.zuo.careweather.R;
import com.yezi.zuo.careweather.Tools.ActivityCollector;

/**
 * Created by zuo on 2016/11/27.
 */

public class MainActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_a);
        ArrayAdapter<String> adapter;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){
            case R.id.shezhi:
                Toast.makeText(getApplicationContext(),"进入设置",Toast.LENGTH_SHORT).show();
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
