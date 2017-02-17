package com.clementedirosa.weathercontentprovider.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

/**
 * Created by clementedirosa on 10/02/2017.
 */

public class DialogDeleteCity extends DialogFragment {

    private static final String ID = "ID_TO_DELETE";

    public interface IDeleteCity {
        void deleteCity(long aId);
    }

    private IDeleteCity mListener = new IDeleteCity() {
        @Override
        public void deleteCity(long aId) {

        }
    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IDeleteCity)
            mListener = (IDeleteCity)context;
    }

    public static DialogDeleteCity getInstance(long aId){
        Bundle vBundle = new Bundle();
        vBundle.putLong(ID, aId);
        DialogDeleteCity vFrag = new DialogDeleteCity();
        vFrag.setArguments(vBundle);
        return vFrag;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder vBuilder = new AlertDialog.Builder(getActivity());
        vBuilder.setTitle("Attenzione")
                .setMessage("Eliminare questa temperatura?")
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.deleteCity(getArguments().getLong(ID));
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
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
