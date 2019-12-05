package com.droidbyme.recyclerviewselection.activity;

import android.Manifest;
import android.app.PendingIntent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.droidbyme.recyclerviewselection.R;

public class SendSms extends AppCompatActivity {

    EditText edtMobileNo, edtMessage;
    Button btnSend, btnClear;
    SharedPreferences permissionStatus;
    private boolean sentToSettings = false;
    private static final int SMS_PERMISSION_CONSTANT = 100;
    private static final int REQUEST_PERMISSION_SETTING = 101;

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_sms);

        edtMessage = (EditText) findViewById(R.id.edtMessage);
        edtMobileNo = (EditText) findViewById(R.id.edtMobileNo);

        btnClear = (Button) findViewById(R.id.btnClear);
        btnSend = (Button) findViewById(R.id.btnSend);

        permissionStatus = getSharedPreferences("permissionStatus", MODE_PRIVATE);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendSmsMessage();
            }
        });
    }

    private void sendSmsMessage() {
        // Check if the Camera permission has been granted
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
            // Permission is already available, start camera preview
            Snackbar.make(btnSend, R.string.call_permission_available, Snackbar.LENGTH_SHORT).show();
            startDialing();
        } else {
            // Permission is missing and must be requested.
            requestCallPermission();
        }
    }

    private void requestCallPermission() {
        // Permission has not been granted and must be requested.
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.SEND_SMS)) {
            // Provide an additional rationale to the user if the permission was not granted
            // and the user would benefit from additional context for the use of the permission.
            // Display a SnackBar with cda button to request the missing permission.
            Snackbar.make(btnSend, R.string.call_access_required, Snackbar.LENGTH_INDEFINITE).setAction(R.string.ok, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Request the permission
                    ActivityCompat.requestPermissions(SendSms.this, new String[]{Manifest.permission.SEND_SMS}, MY_PERMISSIONS_REQUEST_SEND_SMS);
                }
            }).show();

        } else {
            Snackbar.make(btnSend, R.string.call_unavailable, Snackbar.LENGTH_SHORT).show();
            // Request the permission. The result will be received in onRequestPermissionResult().
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, MY_PERMISSIONS_REQUEST_SEND_SMS);
        }
    }

    private void startDialing() {
        String destinationAddress = edtMobileNo.getText().toString();
        String smsMessage = edtMessage.getText().toString();
        String scAddress = null;
        PendingIntent sentIntent = null, deliveryIntent = null;
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(destinationAddress, scAddress, smsMessage, sentIntent, deliveryIntent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        // BEGIN_INCLUDE(onRequestPermissionsResult)
        if (requestCode == MY_PERMISSIONS_REQUEST_SEND_SMS) {
            // Request for camera permission.
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission has been granted. Start camera preview Activity.
                Snackbar.make(btnSend, R.string.call_permission_granted, Snackbar.LENGTH_SHORT).show();
                startDialing();
            } else {
                // Permission request was denied.
                Snackbar.make(btnSend, R.string.call_permission_denied, Snackbar.LENGTH_SHORT).show();
            }
        }
        // END_INCLUDE(onRequestPermissionsResult)
    }

//    public void smsSendMessage(View view) {
////        EditText editText = (EditText) findViewById(R.id.editText_main);
//        // Set the destination phone number to the string in editText.
//        String destinationAddress = edtMobileNo.getText().toString();
//        // Find the sms_message view.
////        EditText smsEditText = (EditText) findViewById(R.id.sms_message);
//        // Get the text of the SMS message.
//        String smsMessage = edtMessage.getText().toString();
//        // Set the service center address if needed, otherwise null.
//        String scAddress = null;
//        // Set pending intents to broadcast
//        // when message sent and when delivered, or set to null.
//        PendingIntent sentIntent = null, deliveryIntent = null;
//        // Check for permission first.
//        checkForSmsPermission();
//        // Use SmsManager.
//        SmsManager smsManager = SmsManager.getDefault();
//        smsManager.sendTextMessage
//                (destinationAddress, scAddress, smsMessage,
//                        sentIntent, deliveryIntent);
//    }
//
//    private void checkForSmsPermission() {
//        if (ActivityCompat.checkSelfPermission(this,
//                Manifest.permission.SEND_SMS) !=
//                PackageManager.PERMISSION_GRANTED) {
//            Log.d("TAG", getString(R.string.permission_not_granted));
//            // Permission not yet granted. Use requestPermissions().
//            // MY_PERMISSIONS_REQUEST_SEND_SMS is an
//            // app-defined int constant. The callback method gets the
//            // result of the request.
//            ActivityCompat.requestPermissions(this,
//                    new String[]{Manifest.permission.SEND_SMS},
//                    MY_PERMISSIONS_REQUEST_SEND_SMS);
//        } else {
//            // Permission already granted. Enable the message button.
////            enableSmsButton();
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode,
//                                           String permissions[], int[] grantResults) {
//        switch (requestCode) {
//            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
//                if (permissions[0].equalsIgnoreCase(Manifest.permission.SEND_SMS)
//                        && grantResults[0] ==
//                        PackageManager.PERMISSION_GRANTED) {
//                    // Permission was granted.
//                } else {
//                    // Permission denied.
//                    Log.d("TAG", getString(R.string.failure_permission));
//                    Toast.makeText(SendSms.this,
//                            getString(R.string.failure_permission),
//                            Toast.LENGTH_SHORT).show();
//                    // Disable the message button.
////                    disableSmsButton();
//                }
//            }
//        }
//    }
}
