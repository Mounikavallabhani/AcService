package com.mansopresk.mansopresk01.acservice.mehandiactivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.mansopresk.mansopresk01.acservice.R;


public class MehendiGuestsCountActivity extends AppCompatActivity{
RadioButton gbutton;
RadioGroup gcount;
    int selectedId;
    SharedPreferences sharedPreferences;
    String strmehgcount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mehendi_guests_count);
        gcount =  (RadioGroup) findViewById(R.id.rgmehguestcount);
        //get selected radio button from radioGroup
        // find the radio button by returned id


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

        gbutton = (RadioButton) findViewById(selectedId);
        sharedPreferences = getSharedPreferences("mehendidetails",MODE_PRIVATE);

        gcount.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                selectedId = mehneedgrp .getCheckedRadioButtonId();
//                mehneedbtn = (RadioButton) findViewById(selectedId);
                final View radioButton = gcount.findViewById(checkedId);
                selectedId = gcount.indexOfChild(radioButton);
                //mehneedbtn = (RadioButton) findViewById(selectedId);

                radioButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int selectdataId = gcount .getCheckedRadioButtonId();
                        gbutton=findViewById(selectdataId);
                        Intent i=new Intent(MehendiGuestsCountActivity.this,MehandiHireActivity.class);
                        strmehgcount=gbutton.getText().toString();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("mehgcountoutput",strmehgcount);
                        editor.apply();
                        startActivity(i);
                    }
                });

            }
        });

    }


}
