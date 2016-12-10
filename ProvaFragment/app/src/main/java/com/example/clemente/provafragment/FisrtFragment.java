package com.example.clemente.provafragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class FisrtFragment extends Fragment {
    private TextView textView;
    private String vDate = "";
    public FisrtFragment() {
        // Required empty public constructor
    }

    public static FisrtFragment newInstance(){
        return new FisrtFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        if (savedInstanceState != null) {
            vDate = savedInstanceState.getString("date");
            Log.d("primo", "non vuoto");
        } else {
            Log.d("primo", "vuoto");
            vDate = new Date().toString();
        }

        View vView = inflater.inflate(R.layout.fragment_fisrt, container, false);

        textView = (TextView) vView.findViewById(R.id.FisrtTW);
        textView.setText(textView.getText() +  vDate);

        return vView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("date", vDate);
    }
}
