package com.clementedirosa.weathercontentprovider.dialog;

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
import android.widget.TextView;
import android.widget.Toast;

import com.clementedirosa.weathercontentprovider.R;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by clementedirosa on 14/01/2017.
 */

public class DialogAddTemperature extends DialogFragment {
    private static final String TITLE = "ATTENZIONE";
    private static final String MESSAGE = "Aggiungi una citta'";
    private static final String CITY_NAME = "City";
    private static final String DATE = "Date";
    private static final String CITY_ID = "This_is_city_id";
    private EditText mTemperatureText;
    private TextView mDateView;
    long mDate;

    public static DialogAddTemperature getInstance(long aCityID){
        DialogAddTemperature vFrag = new DialogAddTemperature();
        Bundle vBundle = new Bundle();
        vBundle.putLong(CITY_ID, aCityID);
        vFrag.setArguments(vBundle);
        return vFrag;
    }

    public interface IAddTemperature {
        void yes(float aTemperature, long aDate, long aCityID);
    }

    private IAddTemperature mListener = new IAddTemperature() {
        @Override
        public void yes(float aTemperature, long aDate, long aCityID) {
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
        if (context instanceof IAddTemperature)
            mListener = (IAddTemperature) context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        AlertDialog.Builder vBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater vInflater = getActivity().getLayoutInflater();
        View vView = vInflater.inflate(R.layout.temperature_dialog, null);
        mTemperatureText = (EditText) vView.findViewById(R.id.temperatureEditText);
        mDateView = (TextView) vView.findViewById(R.id.dateTextView);
        vBuilder.setView(vView);

        if (savedInstanceState != null){
            mTemperatureText.setText(savedInstanceState.getString(CITY_NAME));
            mDate = savedInstanceState.getLong(DATE);
        } else {
            mDate = new Date().getTime();
        }
        Format vFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String vDate = vFormatter.format(mDate);
        mDateView.setText(vDate);

        vBuilder.setTitle(TITLE)
                .setMessage(MESSAGE)
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        if (!mTemperatureText.getText().toString().isEmpty() &&  isNumeric(mTemperatureText.getText().toString())) {
                            mListener.yes(Float.parseFloat(mTemperatureText.getText().toString()), mDate, getArguments().getLong(CITY_ID));
                        } else  {
                            Toast.makeText(getContext(), "Temperatura nulla", Toast.LENGTH_SHORT).show();
                        }
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
        outState.putString(CITY_NAME, mTemperatureText.getText().toString());
        outState.putLong(DATE, mDate);
    }

    public static boolean isNumeric(String str)
    {
        return str.matches("-?\\d+(\\.?\\d+)?");  //match a number with optional '-' and decimal.
    }
}
