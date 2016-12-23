package com.example.cdr.contentprovider.data;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.cdr.contentprovider.R;

/**
 * Created by tsam on 23/12/16.
 */

public class ContactCursorAdapter extends CursorAdapter {

    public ContactCursorAdapter(Context aContext, Cursor aCursor){
        super(aContext,aCursor, false);
    }

    class ViewHolder{
        TextView txt_id, txt_name, txt_surname;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View vView = LayoutInflater.from(context).inflate(R.layout.contact_view, null);

        ViewHolder vViewHolder = new ViewHolder();
        vViewHolder.txt_id = (TextView) vView.findViewById(R.id.text_id);
        vViewHolder.txt_name = (TextView) vView.findViewById(R.id.txt_name);
        vViewHolder.txt_surname = (TextView) vView.findViewById(R.id.txt_surname);
        vView.setTag(vViewHolder);

        return vView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder vHolder = (ViewHolder)view.getTag();
        vHolder.txt_id .setText("" + cursor.getLong(cursor.getColumnIndex(ContactsHelper._ID)));
        vHolder.txt_name.setText(cursor.getString(cursor.getColumnIndex(ContactsHelper.NAME)));
        vHolder.txt_surname.setText(cursor.getString(cursor.getColumnIndex(ContactsHelper.SURNAME)));
    }
}
