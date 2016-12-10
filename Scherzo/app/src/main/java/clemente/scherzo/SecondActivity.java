package clemente.scherzo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    private static final String SAVE_MESSAGE = "save_message";
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        setupGUI();
        if (savedInstanceState != null){
            mTextView.setText(savedInstanceState.getString(SAVE_MESSAGE));
        } else {
            Intent vIntent = getIntent();
            mTextView.setText(new StringBuilder(vIntent.getExtras()
                    .getString(Intent.EXTRA_TEXT)).reverse()
                    .toString());
        }
    }

    private void setupGUI(){
        mTextView = (TextView) findViewById(R.id.message);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(SAVE_MESSAGE, mTextView.getText().toString());
    }
}
