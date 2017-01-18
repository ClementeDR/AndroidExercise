package com.clementedirosa.weathercontentprovider.data;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.clementedirosa.weathercontentprovider.R;

/**
 * Created by clementedirosa on 13/01/2017.
 */

public class CityAdapter extends CursorAdapter{

    public CityAdapter(Context context, Cursor c) {
        super(context, c, false);
    }

    class ViewHolder{
        TextView mCityLabel;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View vView = LayoutInflater.from(context).inflate(R.layout.city_view, null);
        ViewHolder vHolder = new ViewHolder();
        vHolder.mCityLabel = (TextView) vView.findViewById(R.id.cityLabel);
        vView.setTag(vHolder);
        return vView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder vHolder = (ViewHolder)view.getTag();
        vHolder.mCityLabel.setText("" + cursor.getString(cursor.getColumnIndex(CityHelper.NAME)));
    }
}
