package com.example.clemente.masterdetail;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment{


    public interface IUpdateText{
        public void update(String value);

    }
    private IUpdateText mUpdate = new IUpdateText() {
        @Override
        public void update(String value) {

        }

    };

    public BlankFragment() {
        // Required empty public constructor
    }

    public static BlankFragment getInstance(){
        return new BlankFragment();
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
        View vView = inflater.inflate(R.layout.fragment_blank, container, false);

        Button vBtnA = (Button) vView.findViewById(R.id.btnA);
        vBtnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUpdate.update("A");
            }
        });

        Button vBtnB = (Button) vView.findViewById(R.id.btnB);
        vBtnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUpdate.update("B");
            }
        });

        Button vBtnC = (Button) vView.findViewById(R.id.btnC);
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
