package com.example.clemente.androiddialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by clemente on 06/05/16.
 */
public class DialogMultipleChoise extends DialogFragment{

    private ArrayList list;
    private String[] myList = {"A", "B", "C"};

    public static DialogMultipleChoise getInstance(){
        DialogMultipleChoise vFrag = new DialogMultipleChoise();
        return vFrag;
    }

    public interface IOMyDialog{
        void mYes(String aSelected);
        void mNo();
    }

    private IOMyDialog mListener = new IOMyDialog() {
        @Override
        public void mYes(String aSelected) {

        }

        @Override
        public void mNo() {

        }
    };

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof MyDialog.IODialogFrag){
            mListener = (IOMyDialog)activity;
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        list = new ArrayList();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Scegli")
                .setMultiChoiceItems(myList, null,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which,
                                                boolean isChecked) {
                                if (isChecked) {
                                    list.add(which);
                                } else if (list.contains(which)) {
                                    list.remove(Integer.valueOf(which));
                                }
                            }
                        })
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        if(list.size() != 0){
                            String selected = "Hai selezionato: ";
                            for(int i = 0; i < list.size(); i++){

                                selected += myList[Integer.parseInt(list.get(i).toString())] + " ";
                                Log.d("TAG", selected);
                            }
                            mListener.mYes(selected);
                        }else{
                            mListener.mYes("Non hai selezionato niente");
                        }

                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.mNo();
                    }
                });

        return builder.create();



    }
}
