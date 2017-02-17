package com.clementedirosa.weathercontentprovider.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.clementedirosa.weathercontentprovider.R;
import com.clementedirosa.weathercontentprovider.data.CityHelper;
import com.clementedirosa.weathercontentprovider.data.MyContentProvider;

/**
 * Created by clementedirosa on 13/01/2017.
 */

public class DialogUpdateCity extends DialogFragment {
    private static final String CITY_ID = "CITY_ID";
    private static final String CITY_NAME = "save_city";
    private static final String MESSAGE = "Modifica citta'";
    private EditText mCityName;
    private long mPosition;

    public static DialogUpdateCity getInstance(long aID){
        DialogUpdateCity vFrag = new DialogUpdateCity();
        Bundle vBundle = new Bundle();
        vBundle.putLong(CITY_ID, aID);
        vFrag.setArguments(vBundle);
        return vFrag;
    }

    public interface IUpdateCity {
        void update(long aID, String aName);
        void deleteCity(long aId);
    }

    private IUpdateCity mListener = new IUpdateCity() {
        @Override
        public void update(long aPosition, String aName) {
        }

        public void deleteCity(long aId){

        }
    };

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IUpdateCity)
            mListener = (IUpdateCity)context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        AlertDialog.Builder vBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater vInflater = getActivity().getLayoutInflater();
        View vView = vInflater.inflate(R.layout.add_city_dialog, null);
        mCityName = (EditText) vView.findViewById(R.id.nameEditText);

        vBuilder.setView(vView);
        mPosition = getArguments().getLong(CITY_ID);

        if (savedInstanceState != null){
            mCityName.setText(savedInstanceState.getString(CITY_NAME));
        } else {
            Uri vUriQuery = Uri.parse(MyContentProvider.CITY_URI + "/" + mPosition);
            Cursor vCursor = getContext().getContentResolver().query(vUriQuery,null,null,null,null);
            while (vCursor.moveToNext()){
                mCityName.setText("" + vCursor.getString(vCursor.getColumnIndex(CityHelper.NAME)));
            }
            vCursor.close();
        }
        vBuilder.setTitle(AddCityDialog.TITLE)
                .setMessage(MESSAGE)
                .setPositiveButton("SALVA", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.update(mPosition, mCityName.getText().toString());
                    }
                })
                .setNeutralButton("Elimina", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mListener.deleteCity(mPosition);
                    }
                })
                .setNegativeButton("ANNULLA", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });

        return vBuilder.create();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(CITY_NAME, mCityName.getText().toString());
    }


}
