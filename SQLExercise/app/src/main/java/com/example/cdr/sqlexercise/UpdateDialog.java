package com.example.cdr.sqlexercise;

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

/**
 * Created by cdr on 14/12/16.
 */

public class UpdateDialog extends DialogFragment {
    private static final String ID = "Dialog_ID";
    private static final String SURNAME = "SURNAME";
    private static final String NAME = "NAME";
    private int mPosition;
    private EditText mName;
    private EditText mSurname;

    public static UpdateDialog getInstance(int aID){
        UpdateDialog vFrag = new UpdateDialog();
        Bundle vBundle = new Bundle();
        vBundle.putInt(ID, aID);
        vFrag.setArguments(vBundle);
        return vFrag;
    }

    public interface IOUpdateDialog {
        void update(int aID, String aName, String aSurname);
    }

    private IOUpdateDialog mListener = new IOUpdateDialog() {
        @Override
        public void update(int aPosition, String aName, String aSurname) {
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
        if (context instanceof IOUpdateDialog)
            mListener = (IOUpdateDialog)context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);


        AlertDialog.Builder vBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater vInflater = getActivity().getLayoutInflater();
        View vView = vInflater.inflate(R.layout.modify_dialog, null);

        mName = (EditText) vView.findViewById(R.id.txtNameDialog);
        mSurname = (EditText) vView.findViewById(R.id.txtSurnameDialog);
        vBuilder.setView(vView);
        mPosition = getArguments().getInt(ID);

        if (savedInstanceState != null){
            mName.setText(savedInstanceState.getString(NAME));
            mSurname.setText(savedInstanceState.getString(SURNAME));
        } else {
            Contact vContact = DataSet.getInstance(getContext()).getContactByPosition(mPosition);
            mName.setText("" + vContact.getName());
            mSurname.setText("" + vContact.getSurname());
        }

        vBuilder.setTitle(R.string.danger)
            .setMessage(R.string.update_string)
            .setPositiveButton("SALVA", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    mListener.update(mPosition, mName.getText().toString(), mSurname.getText().toString());
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
        outState.putString(NAME, mName.getText().toString());
        outState.putString(SURNAME, mSurname.getText().toString());

    }
}
