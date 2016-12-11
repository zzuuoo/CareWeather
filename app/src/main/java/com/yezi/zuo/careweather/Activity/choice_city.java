package com.yezi.zuo.careweather.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.yezi.zuo.careweather.R;

/**
 * Created by zuo on 2016/12/6.
 */

public class choice_city extends BaseActivity implements View.OnClickListener{

    Button ok;
    EditText editText;
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.choice_place);
        init();

        CityAdapter cityAdapter;
//        if(!zifu.cityArrayList.isEmpty()){
//            cityAdapter = new CityAdapter(choice_city.this,R.layout.city_item,
//                    zifu.cityArrayList);
//        }
//        else{
            cityAdapter = new CityAdapter(choice_city.this,R.layout.city_item,
                    MainActivity.Place.getCity().get(0).get热门());
//        }
        gridView.setAdapter(cityAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                city c= MainActivity.Place.getCity().get(0).get热门().get(position);
                editText.setText(c.getName());

            }
        });
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Toast.makeText(choice_city.this,s.toString()+"b",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                Toast.makeText(choice_city.this,s.toString()+"on",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void afterTextChanged(Editable s) {

                Toast.makeText(choice_city.this,s.toString()+"a",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void init(){
        ok=(Button)findViewById(R.id.certain);
        editText=(EditText)findViewById(R.id.et);
        gridView=(GridView)findViewById(R.id.gv_choicep);
        ok.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.certain:
//                MainActivity.p=editText.getText().toString();
//                MainActivity.uri=MainActivity.u+MainActivity.p+MainActivity.r;
                Intent intent =new Intent();
                intent.putExtra("pl",editText.getText().toString());
                setResult(1,intent);
                finish();
                break;
        }
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
    }
}
