package com.example.clemente.exandroid;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity implements BlankFragment.myInterface{
    TextView textView;
    Fragment myFragment;
    private static final String FRAGMENT = "Fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);

        FragmentManager fragmentManager = getFragmentManager();
        myFragment = fragmentManager.findFragmentByTag(FRAGMENT);

        if(myFragment == null) {
            FragmentTransaction vTrans = fragmentManager.beginTransaction();
            myFragment = BlankFragment.getInstance(123);
            vTrans.add(R.id.container, myFragment, FRAGMENT).commit();
        }
    }

    @Override
    public void updateView(int value){
        textView.setText("" + value);
    }
}
