package com.example.clemente.riepilogo;


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
public class MyFragment extends Fragment {
    private TextView textView;

    public MyFragment() {
        // Required empty public constructor
    }

    public MyFragment getInstance(){
        return new MyFragment();
    }

    public interface IOChangeText{
        void resetText();
    }
    public void resetTextView() {
        textView.setText("");
    }

    IOChangeText mListener = new IOChangeText() {
        @Override
        public void resetText() {

        }
    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof IOChangeText){
            mListener = (IOChangeText)context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vView = inflater.inflate(R.layout.fragment_my, container, false);

        textView = (TextView) vView.findViewById(R.id.textViewMyFrag);



        return vView;
    }

}
