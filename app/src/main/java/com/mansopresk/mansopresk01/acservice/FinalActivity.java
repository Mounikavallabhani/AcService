package com.mansopresk.mansopresk01.acservice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class FinalActivity extends AppCompatActivity {
    TextView finalac;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    TextView showResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        boolean checked = this.getIntent().getBooleanExtra("checkBoxValue", false);
//        finalac=findViewById(R.id.acfinal);
//
//
//        sharedPreferences =  getSharedPreferences("acservicedetails", MODE_PRIVATE);
//
//        String acname = sharedPreferences.getString("acwindow", null);
//
//        String acname1 = sharedPreferences.getString("acsplit", null);
//
//
//        finalac.setText(acname);
//
//        finalac.setText(acname1);
        showResult = (TextView) findViewById(R.id.textview_result);

        Intent intentResult = this.getIntent();

        String monday = intentResult.getStringExtra("installation");
        String tuesday = intentResult.getStringExtra("uninstall");
        String wednesday = intentResult.getStringExtra("cleaning");
        String thursday = intentResult.getStringExtra("service");
        String friday = intentResult.getStringExtra("repair");


        showResult.setText(monday+" , "+tuesday+" , "+wednesday+" , "+thursday+" , "+friday);
    }
    }

