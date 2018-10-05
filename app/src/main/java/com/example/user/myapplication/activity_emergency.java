package com.example.user.myapplication;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class activity_emergency extends AppCompatActivity {


    Button call, sms;
    String phoneNumber = "+355 67 30 49 852";


    TextView result;
    Geocoder geocoder;
    List<Address> addressList;

    // GPSTracker class
    GPSTracker gps;

    Context mContext;

    double latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);


        call = findViewById(R.id.call_button);
        sms = findViewById(R.id.sms_button);
        result = findViewById(R.id.result);

        geocoder = new Geocoder(this, Locale.getDefault());

        mContext = this;

        if (ContextCompat.checkSelfPermission(mContext,
                Manifest.permission.ACCESS_FINE_LOCATION)

                != PackageManager.PERMISSION_GRANTED

                && ActivityCompat.checkSelfPermission(mContext,
                Manifest.permission.ACCESS_COARSE_LOCATION)

                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity_emergency.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        } else {
            // Toast.makeText(mContext, "You need have granted permission",

            //Toast.LENGTH_SHORT).show();
            gps = new GPSTracker(mContext, activity_emergency.this);

            // Check if GPS enabled

            if (gps.canGetLocation()) {

                latitude = gps.getLatitude();
                longitude = gps.getLongitude();

                // \n is for new line

                ////  Toast.makeText(getApplicationContext(),
                // "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                //lati = Double.toString(latitude);
                // longi = Double.toString(longitude);
            } else {
                // Can't get location.
                // GPS or network is not enabled.
                // Ask user to enable GPS/network in settings.

                gps.showSettingsAlert();
            }
        }

        try {
            addressList = geocoder.getFromLocation(latitude, longitude, 1);

            String addressStr = addressList.get(0).getAddressLine(0);


            String fullAddress = addressStr;


            result.setText(fullAddress);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,

                                           int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.

                if (grantResults.length > 0

                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    gps = new GPSTracker(mContext, activity_emergency.this);

                    // Check if GPS enabled

                    if (gps.canGetLocation()) {

                        double latitude = gps.getLatitude();
                        double longitude = gps.getLongitude();

                        // \n is for new line

                        //  Toast.makeText(getApplicationContext(),
                        //  "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                    } else {
                        // Can't get location.

                        // GPS or network is not enabled.

                        // Ask user to enable GPS/network in settings.

                        gps.showSettingsAlert();
                    }

                } else {

                    // permission denied, boo! Disable the

                    // functionality that depends on this permission.
                    // Toast.makeText(mContext, "You need to grant permission",

                    // Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }


    public void onDialButton(View v) {

        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel: +355 67 27 61 107"));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        startActivity(intent);

    }

    public void onClick(View arg0) {

        String address = result.getText().toString();
        Intent intent = new Intent(getApplicationContext(), activity_emergency.class);
        PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, address, pi, null);

        Toast.makeText(getApplicationContext(), "Message Sent successfully!",
                Toast.LENGTH_LONG).show();
    }


}


