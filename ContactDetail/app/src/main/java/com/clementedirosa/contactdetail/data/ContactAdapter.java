package com.clementedirosa.contactdetail.data;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.clementedirosa.contactdetail.R;

/**
 * Created by clementedirosa on 19/01/2017.
 */
public class ContactAdapter extends CursorAdapter {

    public ContactAdapter(Context aContext, Cursor aCursor){
        super(aContext,aCursor, false);
    }

    class ViewHolder{
        TextView mTxt_name, mTxt_surname;
        ImageView mImage;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View vView = LayoutInflater.from(context).inflate(R.layout.contact_view, null);
        ViewHolder vViewHolder = new ViewHolder();
        //vViewHolder.mImage = (ImageView) vView.findViewById(R.id.imageView);
        vViewHolder.mTxt_name = (TextView) vView.findViewById(R.id.nameTxt);
        vViewHolder.mTxt_surname = (TextView) vView.findViewById(R.id.surnameTxt);
        vView.setTag(vViewHolder);

        return vView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder vHolder = (ViewHolder)view.getTag();
        //mImageView.setImage();
        vHolder.mTxt_name.setText(cursor.getString(cursor.getColumnIndex(ContactHelper.NOME)));
        vHolder.mTxt_surname.setText(cursor.getString(cursor.getColumnIndex(ContactHelper.COGNOME)));
    }
}