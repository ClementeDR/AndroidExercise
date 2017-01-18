package com.clementedirosa.weathercontentprovider.data;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.clementedirosa.weathercontentprovider.R;

/**
 * Created by clementedirosa on 14/01/2017.
 */

public class AddCityDialog extends DialogFragment {
    public static final String TITLE = "ATTENZIONE";
    private static final String MESSAGE = "Aggiungi una citta'";
    private static final String CITY_NAME = "City";
    private EditText mNameCity;

    public static AddCityDialog getInstance(){
        AddCityDialog vFrag = new AddCityDialog();
        return vFrag;
    }

    public interface IOAddCity {
        void yes(String aCityName);
    }

    private IOAddCity mListener = new IOAddCity() {
        @Override
        public void yes(String aCityName) {
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
        if (context instanceof IOAddCity)
            mListener = (IOAddCity) context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        AlertDialog.Builder vBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater vInflater = getActivity().getLayoutInflater();
        View vView = vInflater.inflate(R.layout.add_city_dialog, null);
        mNameCity = (EditText) vView.findViewById(R.id.nameEditText);
        vBuilder.setView(vView);
        if (savedInstanceState != null){
            mNameCity.setText(savedInstanceState.getString(CITY_NAME));
        }

        vBuilder.setTitle(TITLE)
                .setMessage(MESSAGE)
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.yes(mNameCity.getText().toString());
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });

        return vBuilder.create();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(CITY_NAME, mNameCity.getText().toString());
    }
}
