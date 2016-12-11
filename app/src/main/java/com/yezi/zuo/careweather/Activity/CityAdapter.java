package com.yezi.zuo.careweather.Activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.yezi.zuo.careweather.R;

import java.util.List;

/**
 * Created by zuo on 2016/12/7.
 */

public class CityAdapter extends ArrayAdapter<city> {
    private int resourceId;
    public CityAdapter(Context context, int resource, List<city> objects) {
        super(context, resource, objects);
        resourceId=resource;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        city cl = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,null);
        TextView city = (TextView)view.findViewById(R.id.city);
        TextView city_key = (TextView)view.findViewById(R.id.city_key);
        city.setText(cl.getName());
        city_key.setText(cl.getKey());
        return view;
    }
}
