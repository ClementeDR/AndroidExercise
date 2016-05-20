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

    private static final String COLOR_A = "COLOR_A";
    private static final String COLOR_B = "COLOR_B";
    private static final String COLOR_C = "COLOR_C";

    private Button vBtnA;
    private Button vBtnB;
    private Button vBtnC;

    private int colorA, colorB, colorC;



    private int randomColor(){
        Random rand = new Random();
        return Color.rgb(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
    }

    public void changeColorNow(String aButtonText){
        switch (aButtonText){
            case "A":
            {
                colorA = randomColor();
                vBtnA.setBackgroundColor(colorA);
                break;
            }
            case "B":
            {
                colorB = randomColor();
                vBtnB.setBackgroundColor(colorB);
                break;
            }
            case  "C":
            {
                colorC = randomColor();
                vBtnC.setBackgroundColor(colorC);
                break;
            }
            default:{

                colorA = randomColor();
                colorB = randomColor();
                colorC = randomColor();
                vBtnA.setBackgroundColor(colorA);
                vBtnB.setBackgroundColor(colorB);
                vBtnC.setBackgroundColor(colorC);
                break;

            }
        }
    }

    public interface IUpdateText{
        void update(String value);

    }
    private IUpdateText mUpdate = new IUpdateText() {
        @Override
        public void update(String value) {

        }

    };

    public ButtonFragment() {
        // Required empty public constructor
        colorA = colorB = colorC = randomColor();
    }

    public static ButtonFragment getInstance(){
        return new ButtonFragment();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(COLOR_A, colorA);
        outState.putInt(COLOR_B, colorB);
        outState.putInt(COLOR_C, colorC);

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

        View vView = inflater.inflate(R.layout.fragment_button, container, false);
        vBtnA = (Button) vView.findViewById(R.id.btnA);
        vBtnB = (Button) vView.findViewById(R.id.btnB);
        vBtnC = (Button) vView.findViewById(R.id.btnC);
        if(savedInstanceState != null){
            colorA = savedInstanceState.getInt(COLOR_A);
            vBtnA.setBackgroundColor(colorA);
            colorB = savedInstanceState.getInt(COLOR_B);
            vBtnB.setBackgroundColor(colorB);
            colorC = savedInstanceState.getInt(COLOR_C);
            vBtnC.setBackgroundColor(colorC);
        }

        vBtnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUpdate.update("A");
            }
        });
        vBtnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUpdate.update("B");
            }
        });
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
