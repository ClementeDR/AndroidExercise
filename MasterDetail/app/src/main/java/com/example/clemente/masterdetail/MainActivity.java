package com.example.clemente.masterdetail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements BlankFragment.IUpdateText{
    private static final String TAG_FRAGMENT = "myFragment";
    private boolean mHasDetail = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(findViewById(R.id.detail_container) != null){
            mHasDetail = true;
        }

        if(getSupportFragmentManager().findFragmentByTag(TAG_FRAGMENT) == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.button_container, BlankFragment.getInstance(), TAG_FRAGMENT)
                    .commit();
        }
    }

    @Override
    public void update(String value) {
        if(mHasDetail){
            getSupportFragmentManager().beginTransaction().replace(R.id.detail_container, NewFrag.getInstance(value)).commit();
        }else {
            getSupportFragmentManager().beginTransaction().replace(R.id.button_container, NewFrag.getInstance(value)).addToBackStack("pippoo").commit();
        }
       /* Intent intent = new Intent(MainActivity.this, NewActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("tasto", value);
        intent.putExtras(bundle);
        startActivity(intent);*/

        /*if(getSupportFragmentManager().findFragmentByTag("newFrag") == null){
            getSupportFragmentManager().beginTransaction().add(R.id.fragment, NewFrag.getInstance(value), "newFrag").commit();
        }*/


    }

}
