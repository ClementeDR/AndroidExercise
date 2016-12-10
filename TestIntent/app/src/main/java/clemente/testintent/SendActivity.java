package clemente.testintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class SendActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);

        TextView vText = (TextView) findViewById(R.id.sms_text);

        Intent vIntent = getIntent();

        vText.setText(vIntent.getExtras().getString(Intent.EXTRA_TEXT));

    }
}
