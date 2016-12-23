package com.example.cdr.contentprovider.data;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by clementedirosa on 23/12/2016.
 */

public class AddContactDialog extends DialogFragment {

    private static final String TITLE = "ATTENZIONE";
    private static final String MESSAGE = "Vuoi aggiungere un contatto?";

    public static AddContactDialog getInstance(){
        AddContactDialog vFrag = new AddContactDialog();
        return vFrag;
    }

    public interface IOAddContact {
        void yes();
    }

    private IOAddContact mListener = new IOAddContact() {
        @Override
        public void yes() {

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
        if (context instanceof IOAddContact)
            mListener = (IOAddContact) context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(TITLE)
                .setMessage(MESSAGE)
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.yes();
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });

        return builder.create();
    }
}
