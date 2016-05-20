package com.example.clemente.riepilogo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements MyFragment.IOChangeText {
    private static final String FRAGMENT = "myFragment";
    MyFragment myFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myFragment = new MyFragment().getInstance();

        if(getSupportFragmentManager().findFragmentByTag(FRAGMENT) == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, myFragment, FRAGMENT)
                    .commit();
        }


        Button reset =(Button) findViewById(R.id.button);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myFragment.resetTextView();
            }
        });
    }


    @Override
    public void resetText() {

    }
}
