package com.example.clemente.contatore;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class DetailActivity extends Activity {

    private static final String TAG = "TEST_ACTIVITY";
    private static final String CONTATORE = "CONTATORE";
    public static final String VALORE = "Prova";

    int counter = 0;
    TextView textView;

    private void launchActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, this + "D:onCreate");

        setContentView(R.layout.activity_detail);
        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            counter = bundle.getInt(VALORE);
        }

        if(savedInstanceState != null){
            counter = savedInstanceState.getInt(CONTATORE);
        }

        textView = (TextView) findViewById(R.id.textView);
        textView.setText("" + counter);

        Button vBtn = (Button) findViewById(R.id.doppio);
        vBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter *= 2;
                textView.setText("" + counter);
            }
        });

        Button back = (Button) findViewById(R.id.buttonBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DetailActivity.super.onBackPressed();

            }
        });

        Button btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent vIntent = new Intent(DetailActivity.this, DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt(DetailActivity.VALORE, counter);
                vIntent.putExtras(bundle);

                startActivity(vIntent);


            }
        });


        Button newActivity = (Button) findViewById(R.id.btn3);
        newActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vIntent = new Intent(DetailActivity.this, NewActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt(NewActivity.VALORE, counter);
                vIntent.putExtras(bundle);

                startActivity(vIntent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, this + "D:onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, this + "D:onResume");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(CONTATORE, counter);
        Log.d(TAG, this + "D:onSave");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, this + "D:onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, this + "D:onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d(TAG, this + "D:onDestroy");
    }
}
