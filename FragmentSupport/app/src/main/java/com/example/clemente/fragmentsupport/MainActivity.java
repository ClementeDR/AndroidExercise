package com.example.clemente.fragmentsupport;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements FragmentSelector.IOnButtonSelecter, FirstDialog.IOnDialogSelected {

    private static final String FRAGMENT_TAG = "current fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG) == null){
            getSupportFragmentManager().beginTransaction()
                .add(R.id.container, FragmentSelector.getInstance("prova"), FRAGMENT_TAG)
                .commit();
        }
    }

    @Override
    public void onUpdateValue(String currentValue) {
        Log.d("TEST", currentValue);
//        FragmentTransaction vTr = getSupportFragmentManager().beginTransaction();
//        //vTr.replace(R.id.container, Fragment_a.getInstance())
//        vTr.replace(R.id.container, FragmentSelector.getInstance(currentValue))
//                .addToBackStack(null)
//                .commit();

        FirstDialog dialog = FirstDialog.getInstance(currentValue);
        dialog.show(getSupportFragmentManager(), "DIALOG");
    }

    @Override
    public void onButtonSelected(String a) {
        getSupportActionBar().setTitle(a);

    }
}
