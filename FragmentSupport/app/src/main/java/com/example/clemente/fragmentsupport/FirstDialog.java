package com.example.clemente.fragmentsupport;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;

/**
 * Created by clemente on 22/04/16.
 */
public class FirstDialog extends DialogFragment {
    public interface IOnDialogSelected{
        public void onButtonSelected(String a);
    }
    public static FirstDialog getInstance(){
        return new FirstDialog();
    }

    public static FirstDialog getInstance(String name){
        FirstDialog frag = new FirstDialog();
        Bundle bundle = new Bundle();
        bundle.putString("NAME", name);
        frag.setArguments(bundle);
        return frag;
    }

    private IOnDialogSelected listener = new IOnDialogSelected() {
        @Override
        public void onButtonSelected(String a) {

        }
    };

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(getActivity() instanceof IOnDialogSelected){
            listener = (IOnDialogSelected)getActivity();
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

       builder.setMessage(getArguments().getString("NAME"))
        .setPositiveButton("Positivo", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int id) {
               Log.d("DIALOG", "" + id);
               listener.onButtonSelected("+++");
           }
       })
        .setNegativeButton("Negativo", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Log.d("DIALOG", "" + id);
                listener.onButtonSelected("---");

            }
        })
        .setNeutralButton("Neutral", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Log.d("DIALOG", "" + id);
                listener.onButtonSelected("???");
            }
        });
        return builder.create();
    }
}

