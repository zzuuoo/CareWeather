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

public class weather_do_adapter extends ArrayAdapter<Index> {
    private int resourceId;


    public weather_do_adapter(Context context, int resource, List<Index> objects) {

        super(context, resource, objects);
        resourceId=resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        Index index = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,null);
        TextView textView = (TextView)view.findViewById(R.id.weather_do);
        TextView textView2 = (TextView)view.findViewById(R.id.weather_dow);
        textView.setText(index.getTitle());
        textView2.setText(index.getZs());

        return view;
    }
}
