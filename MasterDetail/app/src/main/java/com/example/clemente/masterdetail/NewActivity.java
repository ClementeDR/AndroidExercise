package com.example.clemente.masterdetail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class NewActivity extends AppCompatActivity {
    TextView text;
    private String stringa;

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        if(savedInstanceState != null){
            stringa = savedInstanceState.getString("tasto");        }
        text = (TextView) findViewById(R.id.textView);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            stringa = bundle.getString("tasto");
        }

        text.setText(text.getText() + stringa);
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
        outState.putString("tasto", stringa);
    }
}
