/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.smsmessaging;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.Switch;

//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.MapView;
//import com.google.android.gms.maps.OnMapReadyCallback;
//import com.google.android.gms.maps.model.LatLng;
//import com.google.android.gms.maps.model.MarkerOptions;

//import android.support.annotation.NonNull;
//import android.view.Menu;
//import android.view.MenuItem;

import com.google.android.gms.maps.MapView;


/**
 * This app provides SMS features that enable the user to:
 * - Enter a phone number.
 * - Enter a message and send the message to the phone number.
 * - Receive SMS messages and display them in a toast.
 */
public class MainActivity extends AppCompatActivity {

//    private GoogleMap mGoogleMap;
//    private MapView mMapView;
    //private static final String MAPVIEW_BUNDLE_KEY = "MapViewBundleKey";

    private Button button2;
    private Button button3;
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 1;
    User i = new User();
    User u = i.cuser();
    /**
     * Creates the activity, sets the view, and checks for SMS permission.
     *
     * @param savedInstanceState Instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Check to see if SMS is enabled.
        checkForSmsPermission();

        button2 = (Button)findViewById(R.id.profileButton);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                openActivity2();
            }
        });

        button3 = (Button) findViewById(R.id.mapButton);
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void  onClick(View V){
                openActivity3();
            }
        });



//        Bundle mapViewBundle = null;
//        if (savedInstanceState != null) {
//            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY);
//        }
//
//        mMapView = (MapView) findViewById(R.id.map_view); // CHECK THIS
//        mMapView.onCreate(mapViewBundle); // uh oh stinky
//        mMapView.getMapAsync(this);
    }

    public void openActivity2(){
        Intent intent = new Intent(this, ProfilePage.class);
        startActivity(intent);
    }

    public void openActivity3(){
        Intent intent = new Intent(this, MapPage.class);
        startActivity(intent);
    }

    /**
     * Checks whether the app has SMS permission.
     */
    private void checkForSmsPermission() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, getString(R.string.permission_not_granted));
            // Permission not yet granted. Use requestPermissions().
            // MY_PERMISSIONS_REQUEST_SEND_SMS is an
            // app-defined int constant. The callback method gets the
            // result of the request.
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS},
                    MY_PERMISSIONS_REQUEST_SEND_SMS);
        } else {
            // Permission already granted. Enable the SMS button.
            enableSmsButton();
        }
    }

    /**
     * Processes permission request codes.
     *
     * @param requestCode  The request code passed in requestPermissions()
     * @param permissions  The requested permissions. Never null.
     * @param grantResults The grant results for the corresponding permissions
     *                     which is either PERMISSION_GRANTED or PERMISSION_DENIED. Never null.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        // For the requestCode, check if permission was granted or not.
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (permissions[0].equalsIgnoreCase(Manifest.permission.SEND_SMS)
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission was granted. Enable sms button.
                    enableSmsButton();
                } else {
                    // Permission denied.
                    Log.d(TAG, getString(R.string.failure_permission));
                    Toast.makeText(this, getString(R.string.failure_permission),
                            Toast.LENGTH_LONG).show();
                    // Disable the sms button.
                    disableSmsButton();
                }
            }
        }
    }

    /**
     * Defines a string (destinationAddress) for the phone number
     * and gets the input text for the SMS message.
     * Uses SmsManager.sendTextMessage to send the message.
     * Before sending, checks to see if permission is granted.
     *
     * @param view View (message_icon) that was clicked.
     */
    public void smsSendMessage(View view) {

        User a = new User();
        User u = a.cuser();
        a.setpnum("8183196409");
        User b = new User();
        b.setpnum("4153416666");
        EditText editText = (EditText) findViewById(R.id.editText_main);
        // Set the destination phone number to the string in editText.
        String destinationAddress = editText.getText().toString();
        // Find the sms_message view.
        EditText smsEditText = (EditText) findViewById(R.id.sms_message);
        Switch simpleSwitch = (Switch) findViewById(R.id.pub);
        if(simpleSwitch.isChecked())
        {
            destinationAddress = a.getpnum();
        }
        else
            {
                destinationAddress = b.getpnum();
        }

        // Get the text of the sms message.
        u.setMessage(smsEditText.getText().toString());
        String smsMessage = u.toMessage();
                // Set the service center address if needed, otherwise null.
        String scAddress = null;
        // Set pending intents to broadcast
        // when message sent and when delivered, or set to null.
        PendingIntent sentIntent = null, deliveryIntent = null;
        // Check for permission first.
        checkForSmsPermission();
        // Use SmsManager.
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(destinationAddress, scAddress, smsMessage,
                sentIntent, deliveryIntent);
    }

    /**
     * Makes the sms button (message icon) invisible so that it can't be used,
     * and makes the Retry button visible.
     */
    private void disableSmsButton() {
        Toast.makeText(this, R.string.sms_disabled, Toast.LENGTH_LONG).show();
        ImageButton smsButton = (ImageButton) findViewById(R.id.message_icon);
        smsButton.setVisibility(View.INVISIBLE);
        Button retryButton = (Button) findViewById(R.id.button_retry);
        retryButton.setVisibility(View.VISIBLE);
    }

    /**
     * Makes the sms button (message icon) visible so that it can be used.
     */
    private void enableSmsButton() {
        ImageButton smsButton = (ImageButton) findViewById(R.id.message_icon);
        smsButton.setVisibility(View.VISIBLE);
    }

    /**
     * Sends an intent to start the activity.
     *
     * @param view  View (Retry button) that was clicked.
     */
    public void retryApp(View view) {
        Intent intent = getPackageManager().getLaunchIntentForPackage(getPackageName());
        startActivity(intent);
    }

    // GOOGLE MAPS STUFF
//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//
//        Bundle mapViewBundle = outState.getBundle(MAPVIEW_BUNDLE_KEY);
//        if (mapViewBundle == null) {
//            mapViewBundle = new Bundle();
//            outState.putBundle(MAPVIEW_BUNDLE_KEY, mapViewBundle);
//        }
//
//        mMapView.onSaveInstanceState(mapViewBundle);
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        mMapView.onResume();
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        mMapView.onStart();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        mMapView.onStop();
//    }
//
//    @Override
//    public void onMapReady(GoogleMap map) {
//        LatLng ny = new LatLng(40.7143528, -74.0059731);
//        map.addMarker(new MarkerOptions().position(ny).title("Marker"));
//    }
//
//    @Override
//    protected void onPause() {
//        mMapView.onPause();
//        super.onPause();
//    }
//
//    @Override
//    protected void onDestroy() {
//        mMapView.onDestroy();
//        super.onDestroy();
//    }
//
//    @Override
//    public void onLowMemory() {
//        super.onLowMemory();
//        mMapView.onLowMemory();
//    }
}
