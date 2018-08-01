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


public class MehendiRequireForActivity extends AppCompatActivity{
RadioButton reqrb;
RadioGroup reqrg;
int selectedId;
SharedPreferences sharedPreferences;
String strmehreqfor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mehendi_require_for);
        Toolbar mToolbar = (Toolbar)findViewById(R.id.toolbarcredits);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i2 = new Intent(getApplicationContext(),MehendiNeedActivity.class);
                startActivity(i2);
                //finish();
            }
        });


//        SharedPreferences prefs = getSharedPreferences("mehendidetails", MODE_PRIVATE);
//        String restoredText = prefs.getString("mehneedoutput", null);
//        Toast.makeText(this, restoredText, Toast.LENGTH_SHORT).show();
        reqrg =  (RadioGroup) findViewById(R.id.reqrg);
        //get selected radio button from radioGroup
        // find the radio button by returned id
        reqrb = (RadioButton) findViewById(selectedId);
        sharedPreferences = getSharedPreferences("mehendidetails",MODE_PRIVATE);

        reqrg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                selectedId = mehneedgrp .getCheckedRadioButtonId();
//                mehneedbtn = (RadioButton) findViewById(selectedId);
                final View radioButton = reqrg.findViewById(checkedId);
                selectedId = reqrg.indexOfChild(radioButton);
                //mehneedbtn = (RadioButton) findViewById(selectedId);

                radioButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int selectdataId = reqrg .getCheckedRadioButtonId();
                        reqrb=findViewById(selectdataId);
                        Intent i=new Intent(MehendiRequireForActivity.this,MehendiCostsActivity.class);
                        strmehreqfor=reqrb.getText().toString();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("mehreqforoutput",strmehreqfor);
                        editor.apply();
                        startActivity(i);
                    }
                });

            }
        });


    }


}
