package com.example.cdr.googlesunshine;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by cdr on 12/12/16.
 */

public class WeatherAdapter extends BaseAdapter {
    private ArrayList<WeatherItem> mList;
    private Context mContext;

    public WeatherAdapter(Context aContext, ArrayList<WeatherItem> aArrayList){
        mContext = aContext;
        mList = aArrayList;
    }

    public void setList(ArrayList<WeatherItem> list) {
        mList = list;
        notifyDataSetChanged();
    }

    class ViewHolder {
        TextView mTime;
        TextView mTemperature;
        TextView mWeather;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public WeatherItem getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return mList.get(i).getID();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View vView;
        if (view == null){
            LayoutInflater vInflater = LayoutInflater.from(mContext);
            vView = vInflater.inflate(R.layout.weather_row,null);

            ViewHolder vHolder = new ViewHolder();
            vHolder.mTemperature = (TextView) vView.findViewById(R.id.temperatureTxt);

            vHolder.mTime = (TextView) vView.findViewById(R.id.timeTxt);

            vHolder.mWeather = (TextView) vView.findViewById(R.id.weatherTxt);

            vView.setTag(vHolder);
        } else {
            //se view è inizializzata
            vView = view;
        }

        ViewHolder viewHolder = (ViewHolder) vView.getTag();

        WeatherItem vItem = getItem(i);
        Log.d("i" , " " + i + " si " + mList.size());

        viewHolder.mTime.setText(vItem.getTime());

        viewHolder.mWeather.setText(vItem.getWeather());

        viewHolder.mTemperature.setText("" + vItem.getTemperature() + "F");
        Log.d("ADAPTER", "" + vItem.getTemperature());

        return vView;
    }
}
