package com.example.cdr.googlemapsmc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

public class LoginFacebook extends AppCompatActivity {

    private LoginButton mLoginButton;
    private Button mButton;
    CallbackManager mCallbackManage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(getApplication());
        setContentView(R.layout.activity_login_facebook);

        mButton = (Button) findViewById(R.id.log_fb);
        mCallbackManage = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(mCallbackManage, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }

        });



        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               LoginManager.getInstance().logInWithReadPermissions((Activity) getApplicationContext(), Arrays.asList("public_profile","email"));
            }
        });
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManage.onActivityResult(requestCode, resultCode, data);
    }
}
