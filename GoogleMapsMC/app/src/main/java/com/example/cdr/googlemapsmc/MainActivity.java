package com.example.cdr.googlemapsmc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    static final String GOOGLE_MAPS = "AIzaSyCkuQX7qJpl94QKog49V_yVmSHY8DN2qTM";

    //try it
    static final String GOOGLE_MAPS_3 = "AIzaSyA4_aT7hlvmCHUC3ZA4y9c-s7QKMLx8cms";


    Button mGoogleMapsButton;
    Button mLoginFacebookButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUPGUI();

        mGoogleMapsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vIntent = new Intent(MainActivity.this, GoogleMaps.class);
                startActivity(vIntent);
            }
        });

        mLoginFacebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vIntent = new Intent(MainActivity.this, LoginFacebook.class);
                startActivity(vIntent);
            }
        });


    }

    void setUPGUI(){
        mGoogleMapsButton = (Button) findViewById(R.id.btnMaps);
        mLoginFacebookButton = (Button) findViewById(R.id.btn_log_face);
    }
}
