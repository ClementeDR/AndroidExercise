package com.example.cdr.googlesunshine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Singleton.IOGetData {

    private ListView mListView;
    private WeatherAdapter mAdapter;

    private ArrayList<WeatherItem> mlist;
    private Button mBtnDownload;
    private Button mBtnUpdate;
    private Singleton mSingleton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.listView);
      //  mBtnUpdate = (Button) findViewById(R.id.button_update);
        mBtnDownload = (Button) findViewById(R.id.buttonLoad);
        mSingleton = Singleton.getInstance(this);
        mSingleton.setListener(this);

        mlist = new ArrayList<WeatherItem>();
        mAdapter = new WeatherAdapter(this, mSingleton.getList());
        Log.d("Count","size " + mSingleton.getList().size());
        mListView.setAdapter(mAdapter);

        mBtnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               mSingleton.downloadList();
            }
        });

//        mAdapter = new WeatherAdapter(this, mWeatherList);
//
//        mListView.setAdapter(mAdapter);
    }


    @Override
    public void update() {

    }

    @Override
    public void download() {

        mlist = mSingleton.getList();
        mAdapter.setList(mlist);
        mListView.setAdapter(mAdapter);


    }
}
