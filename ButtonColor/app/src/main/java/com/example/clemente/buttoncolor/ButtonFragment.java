package com.example.clemente.buttoncolor;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class ButtonFragment extends Fragment {

    private Button vBtnA;
    private Button vBtnB;
    private Button vBtnC;


    public int randomColor(){
        Random rand = new Random();
        return Color.rgb(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
    }

    public void changeColorNow(String aButtonText){
        switch (aButtonText){
            case "A":
            {
                vBtnA.setBackgroundColor(randomColor());
                break;
            }
            case "B":
            {
                vBtnB.setBackgroundColor(randomColor());
                break;
            }
            case  "C":
            {
                vBtnC.setBackgroundColor(randomColor());
                break;
            }
            default:{
                vBtnA.setBackgroundColor(randomColor());
                vBtnB.setBackgroundColor(randomColor());
                vBtnC.setBackgroundColor(randomColor());
                break;

            }
        }
    }

    public interface IUpdateText{
        public void update(String value);

    }
    private IUpdateText mUpdate = new IUpdateText() {
        @Override
        public void update(String value) {

        }

    };

    public ButtonFragment() {
        // Required empty public constructor
    }

    public static ButtonFragment getInstance(){
        return new ButtonFragment();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof IUpdateText){
            mUpdate = (IUpdateText)getActivity();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vView = inflater.inflate(R.layout.fragment_button, container, false);

        vBtnA = (Button) vView.findViewById(R.id.btnA);
        vBtnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUpdate.update("A");
            }
        });

        vBtnB = (Button) vView.findViewById(R.id.btnB);
        vBtnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUpdate.update("B");
            }
        });

        vBtnC = (Button) vView.findViewById(R.id.btnC);
        vBtnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUpdate.update("C");
            }
        });


        return vView;
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
