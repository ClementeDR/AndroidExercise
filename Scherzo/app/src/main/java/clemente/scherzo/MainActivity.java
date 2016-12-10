package clemente.scherzo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private static String SAVE_TEXT = "save_text";
    private EditText mEditText;
    private Button mBtnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupGUI();
        if (savedInstanceState != null){
            mEditText.setText(savedInstanceState.getString(SAVE_TEXT));
        }

    }

    private void setupGUI(){
        mEditText = (EditText) findViewById(R.id.editText);
        mBtnSend = (Button) findViewById(R.id.btn_send);

        mBtnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendSMS();
            }
        });
    }

    private void sendSMS() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("smsto:3463641259"));
        intent.putExtra(Intent.EXTRA_TEXT, mEditText.getText().toString());
        intent.setType("text/plain");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(SAVE_TEXT, mEditText.getText().toString());
    }
}
