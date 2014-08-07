package com.personal.saifkhan.iapologize;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

    private EditText mPhoneNumberEditTextView;
    private EditText mHowManyEditTextView;
    private EditText mMessageEditTextView;
    private Button mSendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPhoneNumberEditTextView = (EditText)findViewById(R.id.phone_number_edit_text);
        mHowManyEditTextView = (EditText)findViewById(R.id.how_many_edit_text);
        mMessageEditTextView = (EditText)findViewById(R.id.message_edit_text);
        mSendButton = (Button)findViewById(R.id.send_button);
        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!stringCheck(mPhoneNumberEditTextView)){
                    Toast.makeText(MainActivity.this,
                            "Please enter a phone number",
                            Toast.LENGTH_SHORT).show();
                } else if(!stringCheck(mHowManyEditTextView)) {
                    Toast.makeText(MainActivity.this,
                            "Please enter how many times",
                            Toast.LENGTH_SHORT).show();
                } else if(!stringCheck(mMessageEditTextView)) {
                    Toast.makeText(MainActivity.this,
                            "Please enter a message",
                            Toast.LENGTH_SHORT).show();
                } else {

                    AsyncTaskRunner runner = new AsyncTaskRunner();
                    runner.execute;

                    for(int x = 0; x < Integer.parseInt(mHowManyEditTextView.getText().toString()); x ++) {

                    }
                }

            }
        });
    }

    private void sendSMS(String phoneNumber, String message)
    {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null, null);
    }

    private boolean stringCheck(EditText mPhoneNumberEditTextView) {
        if(mPhoneNumberEditTextView.length() > 0) {
            return true;
        } else {
            return false;
        }
    }

}
