package com.example.clemente.androiddialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MyDialog.IODialogFrag, DialogMultipleChoise.IOMyDialog {

    private static final String TAG = "DialogTag";
    private MyDialog myDialog;
    private TextView textView;
    private DialogMultipleChoise multipleChoise;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null){

        }

        textView = (TextView) findViewById(R.id.textView);

        Button vBtn1 = (Button) findViewById(R.id.btn1);
        vBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Bottone 1");
                myDialog = MyDialog.getInstance("TITOLO", "DOMANDA?");
                myDialog.show(getSupportFragmentManager(), "DIALOG");

            }
        });

        Button vBtn2 = (Button) findViewById(R.id.btn2);
        vBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Bottone 2");
                multipleChoise = DialogMultipleChoise.getInstance();
                multipleChoise.show(getSupportFragmentManager(), "MultipleChoise");

            }
        });

        Button vBtn3 = (Button) findViewById(R.id.btn3);
        vBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Bottone 3");

                Toast.makeText(getApplicationContext(), "Hai cliccato btn3", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void yes() {
        textView.setText("HAI CLICCATO SI");
    }

    @Override
    public void no() {
        textView.setText("HAI CLICCATO NO");
    }

    @Override
    public void mYes(String aSelected) {
        textView.setText("" + aSelected);
    }

    @Override
    public void mNo() {
        textView.setText("HAI SELEZIONATO NO -.-");
    }
}
