package com.example.clemente.contatore;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NewActivity extends Activity {

    private static final String TAG = "TEST_ACTIVITY";
    private static final String CONTATORE = "CONTATORE";
    public static final String VALORE = "Prova";

    TextView startText;
    TextView viewMod;
    int counter = 0;
    int startValue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        Bundle bundle = getIntent().getExtras();


        if(bundle != null){
            counter = bundle.getInt(VALORE);
            startValue = counter;

        }


        if(savedInstanceState != null){
            counter = savedInstanceState.getInt(CONTATORE);
        }


        startText = (TextView) findViewById(R.id.startText);
        viewMod = (TextView) findViewById(R.id.textMod);
        viewMod.setText("" + counter);



        startText.setText("" + startValue);

        Button triplica = (Button) findViewById(R.id.triplica);
        triplica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter *= 3;
                viewMod.setText("" + counter);
            }
        });


        Button x4 = (Button) findViewById(R.id.x4);
        x4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter *= 4;
                viewMod.setText("" + counter);
            }
        });

        Button back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });





    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "3:onResume");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(CONTATORE, counter);
        Log.d(TAG, "3: onSave");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "3: onPause");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "3: onStop");
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "3: onStart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "3: onDestroy");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
