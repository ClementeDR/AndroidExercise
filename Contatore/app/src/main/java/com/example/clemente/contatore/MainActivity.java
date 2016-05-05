package com.example.clemente.contatore;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity{

    private int tot;
    private TextView ts;

    private static final String TAG = "TEST_ACTIVITY";
    private static final String Mio_Contatore = "contatore";

    private void updateGUI(){
        ts.setText(""+tot);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(TAG, "M:onCreate");

        setContentView(R.layout.activity_main);
        ts = (TextView) findViewById(R.id.etichetta);


        if(savedInstanceState != null)
            tot = savedInstanceState.getInt(Mio_Contatore);



        updateGUI();
        Button plus = (Button)findViewById(R.id.btn1);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vw) {
                tot++;

                updateGUI();
            }
        });
        Button less = (Button) findViewById(R.id.btn2);
        less.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vw) {

                tot--;

                updateGUI();
            }
        });

        Button res = (Button) findViewById(R.id.reset);
        res.setOnClickListener(new View.OnClickListener(){
            @Override
        public void onClick(View v){
                tot = 0;

                updateGUI();
            }
        });

        Button back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Button goAway = (Button) findViewById(R.id.btnNext);
        goAway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // launchActivity();
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt(DetailActivity.VALORE, tot);
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });

    }

    private void launchActivity(){
        Intent intent = new Intent(this, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(DetailActivity.VALORE, tot);
        intent.putExtras(bundle);

        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v(TAG, "M:onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateGUI();
        Log.v(TAG, "M:onResume");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt(Mio_Contatore, tot);
        Log.d(TAG, "M:onSave");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(TAG, "M:onPause");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.v(TAG, "M:onStop");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(TAG, "M:onDestroy");
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Exit Application?");
        alertDialogBuilder
                .setMessage("Click yes to exit!")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                moveTaskToBack(true);
                                onStop();
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }



}