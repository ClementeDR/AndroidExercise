package com.example.clemente.androiddialog;


import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyDialog extends DialogFragment {

    private static final String TITLE = "title";
    private static final String QUESTION = "question";

    public MyDialog() {
        // Required empty public constructor
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(getActivity() instanceof IODialogFrag){
            mListener = (IODialogFrag) getActivity();
        }
    }

    public interface IODialogFrag{
        void yes();
        void no();
    }

    private IODialogFrag mListener = new IODialogFrag(){

        @Override
        public void yes() {

        }

        @Override
        public void no() {

        }
    };

    public static MyDialog getInstance(String title, String question){

        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        bundle.putString(QUESTION, question);
        MyDialog vFrag = new MyDialog();
        vFrag.setArguments(bundle);
        return vFrag;

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage(getArguments().getString(QUESTION))
                .setTitle(getArguments().getString(TITLE))
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Log.d("DIALOG", "" + id);
                        mListener.yes();
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Log.d("DIALOG", "" + id);
                        mListener.no();

                    }
                });

        return builder.create();
    }
}
