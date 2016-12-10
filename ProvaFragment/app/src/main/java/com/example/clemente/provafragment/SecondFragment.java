package com.example.clemente.provafragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment {
    private TextView textView;
    private String mDate;

    public SecondFragment() {
        // Required empty public constructor
    }

    public static SecondFragment newInstance(){
        return new SecondFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vView = inflater.inflate(R.layout.fragment_second, container, false);

        if(savedInstanceState != null)
            mDate = savedInstanceState.getString("secondDate");
        else
            mDate = new Date().toString();

        textView = (TextView) vView.findViewById(R.id.secondTW);
        textView.setText(textView.getText() + mDate);

        return vView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("secondDate", mDate);
    }
}
