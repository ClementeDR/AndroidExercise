package com.example.cdr.contentprovider.data;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by tsam on 23/12/16.
 */

public class DeleteDialog  extends DialogFragment {
    private static final String ID = "Dialog_ID";
    private static final String TITLE = "ATTENZIONE";
    private static final String MESSAGE = "Vuoi eliminare questo elemento?";
    private long mID;

    public static DeleteDialog getInstance(long aID){
        DeleteDialog vFrag = new DeleteDialog();
        Bundle vBundle = new Bundle();
        vBundle.putLong(ID, aID);
        vFrag.setArguments(vBundle);
        return vFrag;
    }

    public interface IODeleteDialog {
        void delete(long aID);
    }

    private IODeleteDialog mListener = new IODeleteDialog() {
        @Override
        public void delete(long aID) {
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
        if (context instanceof IODeleteDialog)
            mListener = (IODeleteDialog) context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        mID = getArguments().getLong(ID);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(TITLE)
                .setMessage(MESSAGE)
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.delete(mID);
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
