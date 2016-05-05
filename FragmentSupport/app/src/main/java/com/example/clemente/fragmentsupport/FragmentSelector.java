package com.example.clemente.fragmentsupport;


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
public class FragmentSelector extends Fragment {

    public interface IOnButtonSelecter{
        public void onUpdateValue(String currentValue);
    }

    private static final String CURRENT_STRING = "stringa iniziale";
    private static final String START_VALUE = "stringa di partenza";
    private TextView textView;
    private String currentLabel;

    private IOnButtonSelecter listener = new IOnButtonSelecter() {
        @Override
        public void onUpdateValue(String currentValue) {
        }
    };

    public static FragmentSelector getInstance(String startValue){
        FragmentSelector frag = new FragmentSelector();
        Bundle bundle = new Bundle();
        bundle.putString(START_VALUE, startValue);
        frag.setArguments(bundle);

        return frag;
    }

    public static FragmentSelector getInstance(){
        return FragmentSelector.getInstance("--");
    }

    public FragmentSelector() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(getActivity() instanceof IOnButtonSelecter){
            listener = (IOnButtonSelecter)getActivity();
        }
    }

    private void updateGUI(){
        textView.setText(currentLabel);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(savedInstanceState != null){
            currentLabel = savedInstanceState.getString(CURRENT_STRING);
        } else {
            currentLabel = getArguments().getString(START_VALUE);
        }
        View view = inflater.inflate(R.layout.fragment_fragment_selector, container, false);

        textView = (TextView) view.findViewById(R.id.textView);

        Button btnA = (Button) view.findViewById(R.id.btnA);
        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentLabel = "A";

                listener.onUpdateValue(currentLabel);
                updateGUI();
            }

        });
        Button btnB = (Button) view.findViewById(R.id.btnB);
        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentLabel = "B";
                listener.onUpdateValue(currentLabel);
                updateGUI();
            }

        });
        Button btnC = (Button) view.findViewById(R.id.btnC);
        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentLabel = "C";
                listener.onUpdateValue(currentLabel);
                updateGUI();
            }

        });

        updateGUI();
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(CURRENT_STRING, currentLabel);
    }


}
