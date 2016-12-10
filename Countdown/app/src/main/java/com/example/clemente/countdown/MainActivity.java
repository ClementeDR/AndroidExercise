package com.example.clemente.countdown;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements Countdown.IOUpdateTime {
   private static final String FRAGTAG = "CountFrag";
    EditText mEdit;
    TextView mText;
    Button mBtn;
    Countdown vFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mText = (TextView) findViewById(R.id.textView);
        mEdit = (EditText) findViewById(R.id.editText);
        mBtn = (Button)  findViewById(R.id.button);

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vFrag = (Countdown) getSupportFragmentManager().findFragmentByTag(FRAGTAG);
                if(vFrag == null){
                    getSupportFragmentManager().beginTransaction()
                            .add(Countdown.getInstance(Integer.valueOf(mEdit.getText().toString())), FRAGTAG)
                            .commit();
                }

            }
        });



    }

    @Override
    public void updateTime(final int aTime) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mText.setText("" + aTime);

            }
        });
    }
}
