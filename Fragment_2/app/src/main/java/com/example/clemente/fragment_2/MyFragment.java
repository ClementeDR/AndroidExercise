package com.example.clemente.fragment_2;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends Fragment {

    public interface IUpdateText{
        public void updateTextView(int value);
    }
    private IUpdateText mUpdate;
    private static final String TAG = "Fragment";
    private static final String COUNTER = "counter";
    TextView textView;
    int counter;

    public MyFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach");

        Activity hostActivity = getActivity();
        if(context instanceof IUpdateText){
            mUpdate = (IUpdateText)hostActivity;
            Log.d(TAG, "mUpdate vale: " + mUpdate);
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate" + this);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView" + this);
        if(savedInstanceState != null){
            counter = savedInstanceState.getInt(COUNTER);
        }

        View view = inflater.inflate(R.layout.counter_layout, container, false);
        textView = (TextView) view.findViewById(R.id.txt1);
        textView.setText("" + counter);

        Button vButton = (Button) view.findViewById(R.id.button2);
        vButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tastoPremuto();
                Log.d(TAG, "Tasto premuto");
            }
        });


        // Inflate the layout for this fragment
        return view;
    }

    private void tastoPremuto(){
        if(mUpdate != null)
            mUpdate.updateTextView(counter);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated");

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(COUNTER, counter);
        Log.d(TAG, "onSave");

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mUpdate = null;
        Log.d(TAG, "onDetach");

    }

    public void inc(){
        counter++;
        textView.setText("" + counter);

    }

    public void dec(){
        counter--;
        textView.setText("" + counter);
    }
}
