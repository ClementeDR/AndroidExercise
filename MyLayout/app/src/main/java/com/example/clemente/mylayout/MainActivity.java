package com.example.clemente.mylayout;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calcolatrice);

        Button btn = (Button)findViewById(R.id.button);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vw) {

                setContentView(R.layout.activity_main);

                Button back = (Button)findViewById(R.id.button3);
                back.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View vw) {

                        setContentView(R.layout.myfirstlayout);
                    }

                    ;
                });

            }
        });





        Button mPiu = (Button)findViewById(R.id.piu);
        Button mMeno = (Button)findViewById(R.id.meno);


    }

}
