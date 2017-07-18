package com.example.clemente.backgroundtimer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

public class TestFede extends AppCompatActivity {

    TimerFede mTimerFede;
    Button mLap;
    TextView mTotalTime;
    TextView mLapTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_fede);

    }
}
