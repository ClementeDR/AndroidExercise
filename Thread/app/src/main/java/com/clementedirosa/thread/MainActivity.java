package com.clementedirosa.thread;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MAin";
    TextView mTextView;
    Button mButton;
    MyAsyncTask mTask;
    Button mButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.textView);
        mButton = (Button) findViewById(R.id.button);
        mButton2 = (Button) findViewById(R.id.button2);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mTextView.setText("START");
////                new Thread(new Runnable() {
////                    @Override
////                    public void run() {
////                        timerLog();
////                    }
////                }).start();
//
//
//                mTextView.setText("END");

                mTask = new MyAsyncTask();
                mTask.execute();

            }
        });

        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTask != null)
                    mTask.cancel(true);
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    private class MyAsyncTask extends AsyncTask<Void, Integer, String>{

        @Override
        protected String doInBackground(Void... params) {

            int vIndex = 0;
            while (!isCancelled()) {
                try {
                    Thread.sleep(1000);
                    publishProgress(vIndex);
                    vIndex++;
                    Log.d(TAG, "timerLog: " + vIndex);
                } catch (InterruptedException aE) {
                    aE.printStackTrace();
                }

            }
            return "Ho finito di contare";
        }

        @Override
        protected void onPreExecute() {
           mTextView.append(" \n " + "START");
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
           mTextView.append(" \n " + " progress " + values[0]);
        }

        @Override
        protected void onPostExecute(String aS) {
           mTextView.append(" \n " + " Ho Finito");
        }

    }
}
