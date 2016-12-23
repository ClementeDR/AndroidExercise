package com.example.cdr.contentprovider.data;


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

import com.example.cdr.contentprovider.R;

/**
 * Created by tsam on 23/12/16.
 */


public class UpdateDialog extends DialogFragment {
    private static final String ID = "Dialog_ID";
    private static final String SURNAME = "SURNAME";
    private static final String NAME = "NAME";
    private static final String TITLE = "ATTENZIONE";
    private static final String MESSAGE = "Vuoi modificarequesto contatto?";
    private long mPosition;
    private EditText mName;
    private EditText mSurname;

    public static UpdateDialog getInstance(long aID){
        UpdateDialog vFrag = new UpdateDialog();
        Bundle vBundle = new Bundle();
        vBundle.putLong(ID, aID);
        vFrag.setArguments(vBundle);
        return vFrag;
    }



    public interface IOUpdateDialog {
        void update(long aID, String aName, String aSurname);
    }

    private IOUpdateDialog mListener = new IOUpdateDialog() {
        @Override
        public void update(long aPosition, String aName, String aSurname) {
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
        mPosition = getArguments().getLong(ID);

        if (savedInstanceState != null){
            mName.setText(savedInstanceState.getString(NAME));
            mSurname.setText(savedInstanceState.getString(SURNAME));
        } else {

            //TODO: PRENDI DA CONTENT OPPURE PASSAGGIO

            Uri vUriQuery = Uri.parse(ContactContentProvider.CONTACTS_URI + "/" + mPosition);
            Cursor vCursor = getContext().getContentResolver().query(vUriQuery,null,null,null,null);
            while (vCursor.moveToNext()){
                mName.setText("" + vCursor.getString(vCursor.getColumnIndex(ContactsHelper.NAME)));
                mSurname.setText("" + vCursor.getString(vCursor.getColumnIndex(ContactsHelper.SURNAME)));
            }
            vCursor.close();
        }
        vBuilder.setTitle(TITLE)
                .setMessage(MESSAGE)
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