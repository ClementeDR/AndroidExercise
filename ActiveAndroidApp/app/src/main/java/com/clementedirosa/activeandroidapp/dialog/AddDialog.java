package com.clementedirosa.activeandroidapp.dialog;

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

import com.clementedirosa.activeandroidapp.R;

/**
 * Created by clementedirosa on 19/01/2017.
 */

public class AddDialog extends DialogFragment {
    public static final String TITLE = "ATTENZIONE";
    private static final String MESSAGE = "Aggiungi persona'";
    private static final String NAME = "NAME";
    private static final String SURNAME = "SURNAME";
    private EditText mName;
    private EditText mSurname;

    public static AddDialog getInstance(){
        AddDialog vFrag = new AddDialog();
        return vFrag;
    }

    public interface IOAdd {
        void yes(String aName, String aSurname);
    }

    private IOAdd mListener = new IOAdd() {
        @Override
        public void yes(String aName, String aSurname) {
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
        if (context instanceof IOAdd)
            mListener = (IOAdd)context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        AlertDialog.Builder vBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater vInflater = getActivity().getLayoutInflater();
        View vView = vInflater.inflate(R.layout.add_city_dialog, null);
        mName = (EditText) vView.findViewById(R.id.nameEditText);
        mSurname = (EditText) vView.findViewById(R.id.surnameEditText);
        vBuilder.setView(vView);
        if (savedInstanceState != null){
            mName.setText(savedInstanceState.getString(NAME));
            mSurname.setText(savedInstanceState.getString(SURNAME));
        }

        vBuilder.setTitle(TITLE)
                .setMessage(MESSAGE)
                .setPositiveButton("SALVA", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        if (!mName.getText().toString().isEmpty() &&  !mSurname.getText().toString().isEmpty())
                            mListener.yes(mName.getText().toString(), mSurname.getText().toString());
                    }
                })
                .setNeutralButton("Annulla", new DialogInterface.OnClickListener() {
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
