package com.example.clemente.nuovacalcolatrice;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentCalcolatrice extends Fragment {
    public static final String TAG = "Activity";

    public FragmentCalcolatrice() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "F:onAttach");

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate" + this);
    }

    private float tot;
    private TextView ts;
    private float temp;
    private char operator;
    private boolean dotInsert = false;
    private static final String TOTALE = "totale";
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG,"F:onCreateView" + this);

        if(savedInstanceState != null){
            tot = savedInstanceState.getFloat(TOTALE);
        }

        view = inflater.inflate(R.layout.layout_calcolatrice, container, false);

        ts = (TextView) view.findViewById(R.id.textView);
        tot  = Float.parseFloat(ts.getText().toString());

        loadButton();
        Button ce = (Button)view.findViewById(R.id.ce);
        ce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ts.setText("0");
            }
        });

        Button canc = (Button)view.findViewById(R.id.c);
        canc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ts.length() == 1)
                    ts.setText("0");
                else
                    ts.setText("" + ts.getText().toString().substring(0, ts.length() - 1));

            }
        });

        Button mul = (Button) view.findViewById(R.id.mul);
        mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp = Float.valueOf(ts.getText().toString());
                ts.setText("0");
                operator = '*';
                dotInsert = false;
            }
        });

        Button div = (Button) view.findViewById(R.id.div);
        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp = Float.valueOf(ts.getText().toString());
                ts.setText("0");
                operator = '/';
                dotInsert = false;
            }
        });
        Button plus = (Button) view.findViewById(R.id.buttonPlus);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp = Float.valueOf(ts.getText().toString());
                ts.setText("0");
                operator = '+';
                dotInsert = false;
            }
        });
        Button less = (Button) view.findViewById(R.id.buttonLess);
        less.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp = Float.valueOf(ts.getText().toString());
                ts.setText("0");
                operator = '-';
                dotInsert = false;
            }
        });
        Button eguals = (Button)view.findViewById(R.id.buttonEguals);
        eguals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(operator == '-')
                    tot = (temp - Float.parseFloat(ts.getText().toString()));
                if(operator == '+')
                    tot = (temp + Float.parseFloat(ts.getText().toString()));
                if(operator == '*')
                    tot = (temp * Float.parseFloat(ts.getText().toString()));
                else
                    tot = (temp / Float.parseFloat(ts.getText().toString()));
                ts.setText(""+tot);

                dotInsert = false;
            }
        });
        // Inflate the layout for this fragment
        return view;
    }
    private void loadButton(){
        Button b1 = (Button) view.findViewById(R.id.button1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vw) {
                if(ts.getText().equals("0"))

                    ts.setText("1");
                else
                    ts.setText(ts.getText() + "" + 1);
            }
        });

        Button b2 = (Button) view.findViewById(R.id.button2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vw) {
                if(ts.getText().equals("0"))

                    ts.setText("2");
                else
                    ts.setText(ts.getText() + "" + 2);
            }
        });


        Button b3 = (Button) view.findViewById(R.id.button3);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vw) {
                if(ts.getText().equals("0"))

                    ts.setText("3");
                else
                    ts.setText(ts.getText() + "" + 3);
            }
        });


        Button b4 = (Button) view.findViewById(R.id.button4);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vw) {
                if (ts.getText().equals("0"))

                    ts.setText("4");
                else
                    ts.setText(ts.getText() + "" + 4);
            }
        });


        Button b5 = (Button) view.findViewById(R.id.button5);
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vw) {
                if(ts.getText().equals("0"))

                    ts.setText("5");
                else
                    ts.setText(ts.getText() + "" + 5);
            }
        });

        Button b6 = (Button) view.findViewById(R.id.button6);
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vw) {
                if(ts.getText().equals("0"))

                    ts.setText("6");
                else
                    ts.setText(ts.getText() + "" + 6);
            }
        });


        Button b7 = (Button) view.findViewById(R.id.button7);
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vw) {
                if (ts.getText().equals("0"))

                    ts.setText("7");
                else
                    ts.setText(ts.getText() + "" + 7);
            }
        });


        Button b8 = (Button) view.findViewById(R.id.button8);
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vw) {
                if (ts.getText().equals("0"))

                    ts.setText("8");
                else
                    ts.setText(ts.getText() + "" + 8);
            }
        });



        Button b9 = (Button) view.findViewById(R.id.button9);
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vw) {
                if(ts.getText().equals("0"))

                    ts.setText("9");
                else
                    ts.setText(ts.getText() + "" + 9);
            }
        });

        Button b0 = (Button) view.findViewById(R.id.button0);
        b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ts.getText().equals("0"))

                    ts.setText(ts.getText() + "" + 9);
            }
        });

        Button dot = (Button) view.findViewById(R.id.buttonDot);
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
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "F:onActivityCreated");

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "F:onStart");

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "F:onResume");

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putFloat(TOTALE, tot);
        Log.d(TAG, "F:onSave");
    }


    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "F:onPause");

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "F:onStop");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "F:onDestroy");

    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "F:onDetach");

    }





}
