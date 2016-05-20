package com.example.clemente.buttoncolor;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentColor extends Fragment {

    private Button vBtnA;
    private Button vBtnB;
    private Button vBtnC;

    public FragmentColor() {
        // Required empty public constructor
    }



    public interface IOUpdateColor{
        void changeColor(String aButtonText);
    }
    private IOUpdateColor mUpdate = new IOUpdateColor() {
        @Override
        public void changeColor(String aButtonText) {

        }

    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof IOUpdateColor){
            mUpdate = (IOUpdateColor)getActivity();
        }

    }
    public static FragmentColor getInstance(String aValue){
        FragmentColor vFrag = new FragmentColor();
        Bundle bundle = new Bundle();
        bundle.putString("testo", aValue);
        vFrag.setArguments(bundle);
        return  vFrag;

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mUpdate = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vView =inflater.inflate(R.layout.fragment_fragment_color, container, false);

        final TextView vText = (TextView) vView.findViewById(R.id.etichetta);


        Bundle bundle = getArguments();
        //posso evitare di controllare,  da eliminare dopo
        if(bundle != null){
            vText.setText(vText.getText() + bundle.getString("testo"));
        }


        vBtnA = (Button)vView.findViewById(R.id.buttonFragA);
        vBtnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUpdate.changeColor("A");
            }
        });
        vBtnB = (Button)vView.findViewById(R.id.buttonFragB);
        vBtnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUpdate.changeColor("B");
            }
        });
        vBtnC = (Button)vView.findViewById(R.id.buttonFragC);
        vBtnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUpdate.changeColor("C");   
            }
        });

        Button reset = (Button) vView.findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUpdate.changeColor("");
            }
        });

        return vView;
    }

}
