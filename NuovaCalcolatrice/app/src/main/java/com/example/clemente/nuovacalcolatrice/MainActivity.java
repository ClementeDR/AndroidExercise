package com.example.clemente.nuovacalcolatrice;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    private static final String FRAGMENT = "fragmentCalcolatrice";
    FragmentCalcolatrice fragmentCalcolatrice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(FragmentCalcolatrice.TAG, "M:onCreate");

        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentCalcolatrice = (FragmentCalcolatrice) fragmentManager.findFragmentByTag(FRAGMENT);

        if(fragmentCalcolatrice == null) {

            FragmentTransaction vTrans = fragmentManager.beginTransaction();

            fragmentCalcolatrice = new FragmentCalcolatrice();

            vTrans.add(R.id.container, fragmentCalcolatrice, FRAGMENT);
            vTrans.commit();
        }

        Button back = (Button) findViewById(R.id.backMain);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(FragmentCalcolatrice.TAG, "M:onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(FragmentCalcolatrice.TAG, "M:onResume");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(FragmentCalcolatrice.TAG, "M:onSave");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(FragmentCalcolatrice.TAG, "M:onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(FragmentCalcolatrice.TAG, "M:onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(FragmentCalcolatrice.TAG, "M:onDestroy");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.d(FragmentCalcolatrice.TAG, "M:onBack");
    }
}
