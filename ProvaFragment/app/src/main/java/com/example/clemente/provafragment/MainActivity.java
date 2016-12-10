package com.example.clemente.provafragment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String Fisrt_Tag = "first";
    private static final String Second_Tag = "second";
    private FisrtFragment fisrtFragment;
    private SecondFragment secondFragment;
    private boolean start = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (savedInstanceState != null){
            start = savedInstanceState.getBoolean("start");
        }
        if (start)
            firstFrag();

        start = false;

        Button vBtn1 = (Button) findViewById(R.id.button1);
        vBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstFrag();
            }
        });

        Button vBtn2 = (Button) findViewById(R.id.button2);
        vBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               secondFrag();
            }
        });

    }

    private void firstFrag(){
        fisrtFragment = (FisrtFragment) getSupportFragmentManager().findFragmentByTag(Fisrt_Tag);
        if(fisrtFragment == null) {
            fisrtFragment = FisrtFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, fisrtFragment, Fisrt_Tag)
                    .commit();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("start", start);
    }

    private void secondFrag(){
        secondFragment = (SecondFragment) getSupportFragmentManager().findFragmentByTag(Second_Tag);
        if(secondFragment == null) {
            secondFragment = SecondFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, secondFragment
                            , Second_Tag)
                    .commit();
        }
    }
}
