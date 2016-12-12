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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zuo on 2016/12/6.
 */

public class choice_city extends BaseActivity implements View.OnClickListener{

//    Button ok;
    EditText editText;
    GridView gridView;
    List<city> allList;
    List<city> pList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.choice_place);
        init();

        CityAdapter cityAdapter;
//            cityAdapter = new CityAdapter(choice_city.this,R.layout.city_item,
//                    MainActivity.Place.getCity().get(0).get热门());
        cityAdapter = new CityAdapter(choice_city.this,R.layout.city_item,
                allList);
        gridView.setAdapter(cityAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                city c= allList.get(position);
                editText.setText(c.getName());
                Intent intent =new Intent();
                intent.putExtra("pl",editText.getText().toString());
                setResult(1,intent);
                finish();

            }
        });
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                Toast.makeText(choice_city.this,s.toString()+"b",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                pList.clear();
                for(int i = 0; i<allList.size();i++){
                    if(allList.get(i).getName().contains(s)){
                        pList.add(allList.get(i));
                    }
                }
                CityAdapter cityAdapter1;
                cityAdapter1 = new CityAdapter(choice_city.this,R.layout.city_item,
                        pList);
                gridView.setAdapter(cityAdapter1);

                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        city c= pList.get(position);
                        editText.setText(c.getName());
                        Intent intent =new Intent();
                        intent.putExtra("pl",editText.getText().toString());
                        setResult(1,intent);
                        finish();

                    }
                });


//                Toast.makeText(choice_city.this,s.toString()+"on",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void afterTextChanged(Editable s) {

//                Toast.makeText(choice_city.this,s.toString()+"a",Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void init(){
        pList=new ArrayList<>();
        allList=new ArrayList<>();
//        ok=(Button)findViewById(R.id.certain);
        editText=(EditText)findViewById(R.id.et);
        gridView=(GridView)findViewById(R.id.gv_choicep);
//        ok.setOnClickListener(this);

        if(MainActivity.Place!=null){

            for(int i=0;i<MainActivity.Place.getCity().get(0).getA().size();i++)
                allList.add(MainActivity.Place.getCity().get(0).getA().get(i));
            for(int i=0;i<MainActivity.Place.getCity().get(0).getB().size();i++)
                allList.add(MainActivity.Place.getCity().get(0).getB().get(i));
            for(int i=0;i<MainActivity.Place.getCity().get(0).getC().size();i++)
                allList.add(MainActivity.Place.getCity().get(0).getC().get(i));
            for(int i=0;i<MainActivity.Place.getCity().get(0).getD().size();i++)
                allList.add(MainActivity.Place.getCity().get(0).getD().get(i));
            for(int i=0;i<MainActivity.Place.getCity().get(0).getE().size();i++)
                allList.add(MainActivity.Place.getCity().get(0).getE().get(i));
            for(int i=0;i<MainActivity.Place.getCity().get(0).getF().size();i++)
                allList.add(MainActivity.Place.getCity().get(0).getF().get(i));
            for(int i=0;i<MainActivity.Place.getCity().get(0).getG().size();i++)
                allList.add(MainActivity.Place.getCity().get(0).getG().get(i));
            for(int i=0;i<MainActivity.Place.getCity().get(0).getH().size();i++)
                allList.add(MainActivity.Place.getCity().get(0).getH().get(i));
//            for(int i=0;i<MainActivity.Place.getCity().get(0).getI().size();i++)
//                allList.add(MainActivity.Place.getCity().get(0).getI().get(i));
            for(int i=0;i<MainActivity.Place.getCity().get(0).getJ().size();i++)
                allList.add(MainActivity.Place.getCity().get(0).getJ().get(i));
            for(int i=0;i<MainActivity.Place.getCity().get(0).getK().size();i++)
                allList.add(MainActivity.Place.getCity().get(0).getK().get(i));
            for(int i=0;i<MainActivity.Place.getCity().get(0).getL().size();i++)
                allList.add(MainActivity.Place.getCity().get(0).getL().get(i));
            for(int i=0;i<MainActivity.Place.getCity().get(0).getM().size();i++)
                allList.add(MainActivity.Place.getCity().get(0).getM().get(i));
//            for(int i=0;i<MainActivity.Place.getCity().get(0).getO().size();i++)
//                allList.add(MainActivity.Place.getCity().get(0).getO().get(i));
            for(int i=0;i<MainActivity.Place.getCity().get(0).getP().size();i++)
                allList.add(MainActivity.Place.getCity().get(0).getP().get(i));
            for(int i=0;i<MainActivity.Place.getCity().get(0).getQ().size();i++)
                allList.add(MainActivity.Place.getCity().get(0).getQ().get(i));
            for(int i=0;i<MainActivity.Place.getCity().get(0).getR().size();i++)
                allList.add(MainActivity.Place.getCity().get(0).getR().get(i));
            for(int i=0;i<MainActivity.Place.getCity().get(0).getS().size();i++)
                allList.add(MainActivity.Place.getCity().get(0).getS().get(i));
            for(int i=0;i<MainActivity.Place.getCity().get(0).getT().size();i++)
                allList.add(MainActivity.Place.getCity().get(0).getT().get(i));
//            for(int i=0;i<MainActivity.Place.getCity().get(0).getU().size();i++)
////                allList.add(MainActivity.Place.getCity().get(0).getU().get(i));
//            for(int i=0;i<MainActivity.Place.getCity().get(0).getV().size();i++)
//                allList.add(MainActivity.Place.getCity().get(0).getV().get(i));
            for(int i=0;i<MainActivity.Place.getCity().get(0).getW().size();i++)
                allList.add(MainActivity.Place.getCity().get(0).getW().get(i));
            for(int i=0;i<MainActivity.Place.getCity().get(0).getX().size();i++)
                allList.add(MainActivity.Place.getCity().get(0).getX().get(i));
            for(int i=0;i<MainActivity.Place.getCity().get(0).getY().size();i++)
                allList.add(MainActivity.Place.getCity().get(0).getY().get(i));
            for(int i=0;i<MainActivity.Place.getCity().get(0).getZ().size();i++)
                allList.add(MainActivity.Place.getCity().get(0).getZ().get(i));
        }

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
//            case R.id.certain:
//                MainActivity.p=editText.getText().toString();
//                MainActivity.uri=MainActivity.u+MainActivity.p+MainActivity.r;
//                Intent intent =new Intent();
//                intent.putExtra("pl",editText.getText().toString());
//                setResult(1,intent);
//                finish();
//                break;
        }
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
    }
}
