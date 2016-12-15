package com.example.cdr.sqlexercise;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by cdr on 14/12/16.
 */

public class DeleteDialog  extends DialogFragment{
    private static final String ID = "Dialog_ID";
    private int mID;

    public static DeleteDialog getInstance(int aID){
        DeleteDialog vFrag = new DeleteDialog();
        Bundle vBundle = new Bundle();
        vBundle.putInt(ID, aID);
        vFrag.setArguments(vBundle);
        return vFrag;
    }

    public interface IODeleteDialog {
        void delete(int aPosition);
    }

    private IODeleteDialog mListener = new IODeleteDialog() {
        @Override
        public void delete(int aID) {
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

        mID = getArguments().getInt(ID);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //marker android da vedere
        builder.setTitle(R.string.danger)
                .setMessage(R.string.delete_string)
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
