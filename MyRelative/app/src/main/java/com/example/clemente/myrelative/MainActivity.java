package com.example.clemente.myrelative;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    private static final String TAG = "PIPPO";
    private float tot = 0;
    private TextView ts;
    private float temp;
    private char operator;
    private boolean dotInsert = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Log.d(TAG, "testo2");
        ts = (TextView) findViewById(R.id.textView);
        tot  = Float.parseFloat(ts.getText().toString());

        loadButton();
        Button plus = (Button) findViewById(R.id.buttonPlus);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp = Float.valueOf(ts.getText().toString());
                ts.setText("0");
                operator = '+';
                dotInsert = false;
            }
        });
        Button less = (Button) findViewById(R.id.buttonLess);
        less.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp = Float.valueOf(ts.getText().toString());
                ts.setText("0");
                operator = '-';
                dotInsert = false;
            }
        });

        int x = 5;

        Button eguals = (Button)findViewById(R.id.buttonEguals);
        eguals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(operator == '-')
                    tot = (temp - Float.parseFloat(ts.getText().toString()));
                else
                    tot = (temp + Float.parseFloat(ts.getText().toString()));
                ts.setText(""+tot);

                dotInsert = false;

                 //x == 5 ? System.out.println("Ciao") : System.out.println("prova");
            }
        });


    }

    private void loadButton(){

        Button back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });

        Button b1 = (Button) findViewById(R.id.button1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vw) {
                if(ts.getText().equals("0"))

                    ts.setText("1");
                else
                    ts.setText(ts.getText() + "" + 1);
            }
        });

        Button b2 = (Button) findViewById(R.id.button2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vw) {
                if(ts.getText().equals("0"))

                    ts.setText("2");
                else
                    ts.setText(ts.getText() + "" + 2);
            }
        });


        Button b3 = (Button) findViewById(R.id.button3);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vw) {
                if(ts.getText().equals("0"))

                    ts.setText("3");
                else
                    ts.setText(ts.getText() + "" + 3);
            }
        });


        Button b4 = (Button) findViewById(R.id.button4);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vw) {
                if(ts.getText().equals("0"))

                    ts.setText("4");
                else
                    ts.setText(ts.getText() + "" + 4);
            }
        });


        Button b5 = (Button) findViewById(R.id.button5);
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vw) {
                if(ts.getText().equals("0"))

                    ts.setText("5");
                else
                    ts.setText(ts.getText() + "" + 5);
            }
        });

        Button b6 = (Button) findViewById(R.id.button6);
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vw) {
                if(ts.getText().equals("0"))

                    ts.setText("6");
                else
                    ts.setText(ts.getText() + "" + 6);
            }
        });


        Button b7 = (Button) findViewById(R.id.button7);
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vw) {
                if(ts.getText().equals("0"))

                    ts.setText("7");
                else
                    ts.setText(ts.getText() + "" + 7);
            }
        });


        final Button b8 = (Button) findViewById(R.id.button8);
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vw) {
                if(ts.getText().equals("0"))

                    ts.setText(b8.getText());
                else
                    ts.setText(ts.getText() + "" + b8.getText());
            }
        });



        Button b9 = (Button) findViewById(R.id.button9);
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vw) {
                if(ts.getText().equals("0"))

                    ts.setText("9");
                else
                    ts.setText(ts.getText() + "" + 9);
            }
        });

        Button b0 = (Button) findViewById(R.id.button0);
        b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ts.getText().equals("0"))

                    ts.setText(ts.getText() + "" + 9);
            }
        });

        Button dot = (Button) findViewById(R.id.buttonDot);
        dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!dotInsert)
                     ts.setText(ts.getText() + ".");

                dotInsert = true;
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}
