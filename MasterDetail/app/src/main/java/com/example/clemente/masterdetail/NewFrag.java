package com.example.clemente.masterdetail;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewFrag extends Fragment {


    public NewFrag() {
        // Required empty public constructor
    }



    public interface IOUpdateColor{
        public void changeColor();
    }
    private IOUpdateColor mUpdate = new IOUpdateColor() {
        @Override
        public void changeColor() {

        }

    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof IOUpdateColor){
            mUpdate = (IOUpdateColor)getActivity();
        }

    }
    public static NewFrag getInstance(String aValue){
        NewFrag vFrag = new NewFrag();
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
        View vView =inflater.inflate(R.layout.fragment_new, container, false);

        TextView vText = (TextView) vView.findViewById(R.id.etichetta);
        Bundle bundle = getArguments();
        if(bundle != null){
            vText.setText(vText.getText() + bundle.getString("testo"));
        }


        return vView;
    }

}
