package com.example.clemente.exandroid;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class BlankFragment extends Fragment {
    private int counter;
    private static final String COUNTER = "counter";
    public static final String START = "ljbn";
    public interface myInterface{
        public void updateView(int value);
    }

    private myInterface mUpdate;
    public static BlankFragment getInstance(int start){
        BlankFragment vFrag = new BlankFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(START, start);
        vFrag.setArguments(bundle);
        return vFrag;
    }

    public BlankFragment(){   }

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        if(context instanceof myInterface){
            mUpdate = (myInterface)context;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(savedInstanceState != null){
            counter = savedInstanceState.getInt("counter");
        }
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        Bundle bundle = getArguments();
        if(bundle != null){
            counter = bundle.getInt(START);
        }
        if(savedInstanceState != null){
            counter = savedInstanceState.getInt("counter");
        }
        click(counter);
        Button plus = (Button) view.findViewById(R.id.plus);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                click(counter);
            }
        });
        Button less = (Button) view.findViewById(R.id.less);
        less.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter--;
                click(counter);
            }
        });

        return view;
    }

    private void click(int value){
        if(mUpdate != null)
            mUpdate.updateView(value);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(COUNTER, counter);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mUpdate = null;
    }

}
