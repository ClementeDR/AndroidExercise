package clemente.activitytask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Date;

public abstract class BaseActivity extends Activity {

    private static final String DATE = "DATE";
    private String mCreate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // this.getActionBar().setTitle(this.getName());
        if (savedInstanceState == null){
            Date vDate = new Date();
            mCreate = vDate.toString();
        } else {
            mCreate = savedInstanceState.getString(DATE);
        }

        setupGUI();
    }

    private void setupGUI(){
        Button vBtnA = (Button) findViewById(R.id.btn_a);
        vBtnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchA();
            }
        });
        Button vBtnB = (Button) findViewById(R.id.btn_b);
        vBtnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchB();
            }
        });
        Button vBtnC = (Button) findViewById(R.id.btn_c);
        vBtnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchC();
            }
        });
        Button vBtnD = (Button) findViewById(R.id.btn_d);
        vBtnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchD();
            }
        });
        Button vBtnMain = (Button) findViewById(R.id.btn_main);
        vBtnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchMain();
            }
        });
        Button vBtnNot = (Button) findViewById(R.id.btn_notification);
        vBtnNot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchNotification();
            }
        });

        TextView vTextDate = (TextView) findViewById(R.id.txt_date);
        vTextDate.setText(mCreate);

        TextView vTextName = (TextView) findViewById(R.id.txt_name_activity);
        vTextName.setText(getName());
    }

    protected abstract String getName();

    protected void launchA() {
        Intent vIntent = new Intent(this, ActivityA.class);
        vIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      //  vIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        vIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        //task e top vengono combinate
        startActivity(vIntent);

    }
    protected void launchB() {
        Intent vIntent = new Intent(this, ActivityB.class);
        //vIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(vIntent);
    }
    protected void launchC() {
        Intent vIntent = new Intent(this, ActivityC.class);
        startActivity(vIntent);
    }
    protected void launchD() {
        Intent vIntent = new Intent(this, ActivityD.class);
        startActivity(vIntent);
    }
    protected void launchMain() {
        Intent vIntent = new Intent(this, MainActivity.class);
        startActivity(vIntent);
    }
    protected void launchNotification() {

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(DATE, mCreate);
    }

}
