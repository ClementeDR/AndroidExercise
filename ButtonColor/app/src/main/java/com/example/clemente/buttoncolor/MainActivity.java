package com.example.clemente.buttoncolor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements FragmentColor.IOUpdateColor, ButtonFragment.IUpdateText {

    private static final String TAG_FRAGMENT = "myFragment";
    private boolean mHasDetail = false;
    private ButtonFragment mButtonFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(findViewById(R.id.detail_container) != null){
            mHasDetail = true;
        }
        mButtonFragment = ButtonFragment.getInstance();
        if(getSupportFragmentManager().findFragmentByTag(TAG_FRAGMENT) == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.button_container, mButtonFragment, TAG_FRAGMENT)
                    .commit();
        }
    }

    @Override
    public void update(String value) {
        if(mHasDetail){
            getSupportFragmentManager().beginTransaction().replace(R.id.detail_container, FragmentColor.getInstance(value)).commit();
        }else {
            getSupportFragmentManager().beginTransaction().replace(R.id.button_container, FragmentColor.getInstance(value)).addToBackStack("pippoo").commit();
        }
    }

    @Override
    public void changeColor(String aButtonText) {
        mButtonFragment.changeColorNow(aButtonText);
    }
}
