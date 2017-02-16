package com.clementedirosa.weathercontentprovider.data;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

/**
 * Created by clementedirosa on 10/02/2017.
 */

public class DialogDeleteTemperature extends DialogFragment {

    private static final String ID = "ID_TO_DELETE";

    public interface IODeleteTemperature{
        void deleteTemperature(long aID);
    }

    private IODeleteTemperature mListener = new IODeleteTemperature() {
        @Override
        public void deleteTemperature(long aID) {

        }
    };

    public static DialogDeleteTemperature getInstance(long aID){
        Bundle vBundle = new Bundle();
        vBundle.putLong(ID, aID);
        DialogDeleteTemperature vFrag = new DialogDeleteTemperature();
        vFrag.setArguments(vBundle);
        return vFrag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder vBuilder = new AlertDialog.Builder(getActivity());

        vBuilder.setTitle("Attenzione")
                .setMessage("Eliminare questa temperatura?")
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.deleteTemperature(getArguments().getLong(ID));
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
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IODeleteTemperature)
            mListener = (IODeleteTemperature)context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
