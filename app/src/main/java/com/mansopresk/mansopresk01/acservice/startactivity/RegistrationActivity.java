package com.mansopresk.mansopresk01.acservice.startactivity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mansopresk.mansopresk01.acservice.R;

import java.util.List;
import java.util.Locale;


public class RegistrationActivity extends Activity implements LocationListener {
    TextView textInputEditTextName,textInputEditTextEmail,textInputEditTextPassword,textInputEditTextMobileNumber,appCompatTextViewLoginLink;
    RadioGroup radioGroupUserType;
    Button appCompatButtonRegister;
    SharedPreferences.Editor editor;
    EditText etclocation;
    ImageView currentlocimg;
    LocationManager locationManager;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        textInputEditTextName=findViewById(R.id.textInputEditTextName);
        textInputEditTextEmail=findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword=findViewById(R.id.textInputEditTextPassword);
        textInputEditTextMobileNumber=findViewById(R.id.textInputEditTextMobileNumber);

        etclocation=findViewById(R.id.signupclocation);
        currentlocimg=findViewById(R.id.currentlocimage);
        //appCompatTextViewLoginLink=findViewById(R.id.appCompatTextViewLoginLink);
        appCompatButtonRegister=findViewById(R.id.appCompatButtonRegister);
        radioGroupUserType=findViewById(R.id.radioGroupUserType);

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 101);

        }
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.GET_ACCOUNTS) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.GET_ACCOUNTS)) {

            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.GET_ACCOUNTS}, 101);

            }
        }
        getLocation();

        currentlocimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etclocation.setText("");
                getLocation();
            }
        });





        appCompatButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textInputEditTextName.getText().toString().isEmpty()) {
                    textInputEditTextName.requestFocus();
                    textInputEditTextName.setError("please provide name");
                } else if (textInputEditTextEmail.getText().toString().isEmpty()) {
                    textInputEditTextEmail.requestFocus();
                    textInputEditTextEmail.setError("please provide email id");
                } else if (textInputEditTextMobileNumber.length() != 10) {
                    showSnackbar(textInputEditTextMobileNumber, "Please enter 10 digit mobile number", 4000);

                } else if (textInputEditTextPassword.getText().toString().isEmpty()) {
                    textInputEditTextPassword.requestFocus();
                    textInputEditTextPassword.setError("please provide email id");

                }
                else if (etclocation.getText().toString().isEmpty()) {
                    etclocation.requestFocus();
                    etclocation.setError("please provide location");

                }
                  else {
                    String name=textInputEditTextName.getText().toString();
                    String email = textInputEditTextEmail.getText().toString();
                    String mobileno=textInputEditTextMobileNumber.getText().toString();


                    if (email.contains("@") && email.contains((".com"))) {
                        editor = getSharedPreferences("Registration", MODE_PRIVATE).edit();
                        editor.putString("username", name);
                        editor.putString("mobile", mobileno);
                        editor.putString("email", email);
                        editor.commit();
                        Intent i = new Intent(RegistrationActivity.this, LoginActivity.class);
                        startActivity(i);
                    } else {
                        showSnackbar(textInputEditTextEmail, "provide proper email id", 4000);
                    }
                }

            }
        });







    }
    public void showSnackbar(View view, String message, int duration) {
        Snackbar.make(view, message, duration).show();
    }

    void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 500, 5, this);
            //pdloading.dismiss();
        }
        catch(SecurityException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onLocationChanged(Location location) {
        try {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            etclocation.setText("");
            etclocation.setText(etclocation.getText() + "\n"+addresses.get(0).getAddressLine(0));
        }catch(Exception e)
        {

        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }





}
