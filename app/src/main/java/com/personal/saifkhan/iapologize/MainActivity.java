package com.personal.saifkhan.iapologize;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {

    private EditText mPhoneNumberEditTextView;
    private EditText mHowManyEditTextView;
    private EditText mMessageEditTextView;
    private Button mSendButton;
    private View mSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPhoneNumberEditTextView = (EditText)findViewById(R.id.phone_number_edit_text);
        mHowManyEditTextView = (EditText)findViewById(R.id.how_many_edit_text);
        mMessageEditTextView = (EditText)findViewById(R.id.message_edit_text);
        mSendButton = (Button)findViewById(R.id.send_button);
        mSpinner = findViewById(R.id.spinner);
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
                    mSpinner.setVisibility(View.VISIBLE);

                    hideKeyboardFrom(mPhoneNumberEditTextView);
                    hideKeyboardFrom(mMessageEditTextView);
                    hideKeyboardFrom(mHowManyEditTextView);

                    AsyncTaskRunner runner = new AsyncTaskRunner();
                    runner.execute();
                }

            }
        });
    }

    private void hideKeyboardFrom(EditText editText) {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    public class AsyncTaskRunner extends AsyncTask<String, String, String> {


        @Override
        protected String doInBackground(String... params) {
            for(int x = 0; x < Integer.parseInt(mHowManyEditTextView.getText().toString()); x ++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sendSMS(mPhoneNumberEditTextView.getText().toString(), mMessageEditTextView.getText().toString());
            }
            return "";

        }


        @Override
        protected void onPostExecute(String result) {
            showResult();
        }
    }

    private void showResult() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mSpinner.setVisibility(View.GONE);

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Sent!")
                        .setMessage("You sent " + mHowManyEditTextView.getText().toString() + " messages to " +mPhoneNumberEditTextView.getText().toString())
                        .setNeutralButton("OK", null)
                        .setIcon(android.R.drawable.ic_dialog_dialer)
                        .show();
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
