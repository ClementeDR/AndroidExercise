package com.example.clemente.fragmentnogui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private static final String FRAG_TAG = "fragr";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BlankFragment blankFragment = (BlankFragment) getSupportFragmentManager().findFragmentByTag(FRAG_TAG);
        if (blankFragment == null){
            blankFragment = BlankFragment.getInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(blankFragment, FRAG_TAG)
                    .commit();
        }
    }
}
