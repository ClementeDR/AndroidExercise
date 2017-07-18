package com.example.clemente.backgroundtimer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements TimerFragment.IOTimerUpdate {
    TextView mText;
    TimerFragment timerFragment;
    private static final String TIMER_TAG = "timerTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mText = (TextView) findViewById(R.id.textView);
        timerFragment = (TimerFragment) getSupportFragmentManager().findFragmentByTag(TIMER_TAG);
        if (timerFragment == null) {
            timerFragment = TimerFragment.getInstance();
            getSupportFragmentManager().beginTransaction()
                    .add( timerFragment, TIMER_TAG)
                    .commit();
        }

        Button vButton = (Button) findViewById(R.id.button);
        vButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timerFragment != null)
                    timerFragment.start();
            }
        });



    }

    private void updateView(int aValue){
        mText.setText("" + aValue);
    }


    @Override
    public void onUpdateValue(final String aValue) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mText.setText("" + aValue);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
