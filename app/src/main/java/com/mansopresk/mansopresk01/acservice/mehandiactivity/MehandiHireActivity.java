package com.mansopresk.mansopresk01.acservice.mehandiactivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.mansopresk.mansopresk01.acservice.R;


public class MehandiHireActivity extends AppCompatActivity {
RadioGroup mehhirerg;
RadioButton mehhirerb;
int selectedId;
SharedPreferences sharedPreferences;
String strmehhire;
Button submitbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mehandi_hire);

        Toolbar mToolbar = (Toolbar)findViewById(R.id.toolbarcredits);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i2 = new Intent(getApplicationContext(),MehendiCostsActivity.class);
                startActivity(i2);
                //finish();
            }
        });

        mehhirerg =  (RadioGroup) findViewById(R.id.mehhirerg);
        //get selected radio button from radioGroup
        // find the radio button by returned id
        mehhirerb = (RadioButton) findViewById(selectedId);
        submitbtn=findViewById(R.id.mehsub);
        sharedPreferences = getSharedPreferences("mehendidetails",MODE_PRIVATE);
        mehhirerg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                selectedId = mehneedgrp .getCheckedRadioButtonId();
//                mehneedbtn = (RadioButton) findViewById(selectedId);
                final View radioButton = mehhirerg.findViewById(checkedId);
                selectedId = mehhirerg.indexOfChild(radioButton);
                //mehneedbtn = (RadioButton) findViewById(selectedId);

                radioButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int selectdataId = mehhirerg .getCheckedRadioButtonId();
                        mehhirerb=findViewById(selectdataId);
                        //Intent i=new Intent(MehandiHireActivity.this,MehendiCostsActivity.class);
                        strmehhire=mehhirerb.getText().toString();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("mehhireoutput",strmehhire);
                        editor.apply();
                        //startActivity(i);
                    }
                });


            }
        });
        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
        SharedPreferences prefs = getSharedPreferences("mehendidetails", MODE_PRIVATE);
        String restoredText = prefs.getString("mehneedoutput", null);
        String hiretext = prefs.getString("mehhireoutput", null);
        String costtext = prefs.getString("mehcostoutput", null);
        String reqtext=prefs.getString("mehreqforoutput",null);
        String guestcount=prefs.getString("mehgcountoutput",null);
                    Toast.makeText(MehandiHireActivity.this, guestcount, Toast.LENGTH_SHORT).show();
                    String mail =  "lvssrinivas@gmail.com";
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", mail, null));
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Requirement of Service");
                    if(guestcount==null||guestcount==""){
                        emailIntent.putExtra(Intent.EXTRA_TEXT   ,"Mehendi Details:\n"+ restoredText+","+reqtext+","+costtext+","+hiretext);

                    }else
                    emailIntent.putExtra(Intent.EXTRA_TEXT   ,"Mehendi Details:\n"+ restoredText+","+reqtext+","+costtext+","+guestcount+","+hiretext);
                    try {
                        getApplicationContext().getSharedPreferences("mehendidetails", 0).edit().clear().apply();
                        startActivity(emailIntent);

                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(getApplicationContext(), "Mail account not configured", Toast.LENGTH_LONG).show();
                    }
                    emailIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                } catch (android.content.ActivityNotFoundException ex) {
                    Log.e("Error","Exveption");
                   // Toast.makeText(getApplicationContext(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
