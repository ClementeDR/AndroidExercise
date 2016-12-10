package clemente.testintent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_SELECT_CONTACT = 165;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupGUI();
    }

    private void setupGUI() {
        Button vBtnWeb = (Button) findViewById(R.id.btn_web);
        vBtnWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickWeb();
            }
        });

        Button vBtnSms = (Button) findViewById(R.id.btn_sms);
        vBtnSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendSms();
            }
        });

        Button vBtnnPick = (Button) findViewById(R.id.btn_pick);
        vBtnnPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickController();
            }
        });
    }

    private void pickController() {

        Intent vIntent = new Intent(Intent.ACTION_PICK)
                .setType(ContactsContract.Contacts.CONTENT_TYPE);

        if (vIntent.resolveActivity(getPackageManager()) != null) {

            startActivityForResult(vIntent, REQUEST_SELECT_CONTACT);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_SELECT_CONTACT && resultCode == RESULT_OK){

            Log.d("PICK", "ED");
        }
    }

    private void sendSms() {
//        Intent intent = new Intent(Intent.ACTION_SENDTO);
//        intent.setData(Uri.parse("smsto:3463641259"));  // This ensures only SMS apps respond
//        intent.putExtra("sms_body", "ciao");
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivity(intent);
//        }


        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("smsto:3463641259"));  // This ensures only SMS apps respond
        intent.putExtra(Intent.EXTRA_TEXT, "sms");
        intent.setType("text/plain");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }


    }

    private void clickWeb() {
        Uri vUri = Uri.parse("http://www.google.com");
        Intent vIntent = new Intent(Intent.ACTION_VIEW, vUri);
        if (vIntent.resolveActivity(getPackageManager()) != null){
            startActivity(Intent.createChooser(vIntent, "scegli"));
        }
    }
}
