package com.example.cdr.sqlexercise;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by cdr on 14/12/16.
 */

public class ContactsAdapter extends BaseAdapter {
    private ArrayList<Contact> mData;
    private Context mContext;

    public ContactsAdapter(Context aContext, ArrayList<Contact> aData){
        mContext = aContext;
        mData = aData;
    }

    public void setList(ArrayList<Contact> aData) {
        this.mData = aData;

        notifyDataSetChanged();
        Log.d("Adapter", "setList + " + aData.size());
    }

    class ViewHolder {
        TextView mName;
        TextView mSurname;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View vView;

        if (view == null){
            LayoutInflater vInflater = LayoutInflater.from(mContext);
            vView = vInflater.inflate(R.layout.contact_view,null);

            ViewHolder vHolder = new ViewHolder();
            vHolder.mName = (TextView) vView.findViewById(R.id.txt_name);
            vHolder.mSurname = (TextView) vView.findViewById(R.id.txt_surname);

            vView.setTag(vHolder);
        } else {
            //se view è inizializzata
            vView = view;
        }

        ViewHolder viewHolder = (ViewHolder) vView.getTag();

        Contact vItem = getItem(i);

        viewHolder.mSurname.setText("" + vItem.getSurname());

        viewHolder.mName.setText("" + vItem.getName());

        return vView;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Contact getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return mData.get(i).getID();
    }

    public int getItemPositionById(int aID){
       for (int i = 0; i < mData.size(); i++){
           if (mData.get(i).getID() == aID)
               return i;
       }
        return -1;
    }
}
