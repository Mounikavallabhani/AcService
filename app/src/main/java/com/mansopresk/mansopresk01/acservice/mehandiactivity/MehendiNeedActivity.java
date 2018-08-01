package com.mansopresk.mansopresk01.acservice.mehandiactivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.mansopresk.mansopresk01.acservice.MainActivity;
import com.mansopresk.mansopresk01.acservice.R;


public class MehendiNeedActivity extends AppCompatActivity {
    Button mneednext;
    RadioButton mehneedbtn;
    public RadioGroup mehneedgrp;
    String strmehfuntype;
    int selectedId;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mehendi_need);
        mehneedgrp =  (RadioGroup) findViewById(R.id.mehfunradiogrp);
        Toolbar mToolbar = (Toolbar)findViewById(R.id.toolbarcredits);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i2 = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i2);
                //finish();
            }
        });
        //get selected radio button from radioGroup
        // find the radio button by returned id
        mehneedbtn = (RadioButton) findViewById(selectedId);
        sharedPreferences = getSharedPreferences("mehendidetails",MODE_PRIVATE);

        mehneedgrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                selectedId = mehneedgrp .getCheckedRadioButtonId();
//                mehneedbtn = (RadioButton) findViewById(selectedId);
                final View radioButton = mehneedgrp.findViewById(checkedId);
                selectedId = mehneedgrp.indexOfChild(radioButton);
                //mehneedbtn = (RadioButton) findViewById(selectedId);

                radioButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int selectdataId = mehneedgrp .getCheckedRadioButtonId();
                   mehneedbtn=findViewById(selectdataId);
                Intent i=new Intent(MehendiNeedActivity.this,MehendiRequireForActivity.class);
                        strmehfuntype=mehneedbtn.getText().toString();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                       editor.putString("mehneedoutput",strmehfuntype);
                        editor.apply();
                startActivity(i);
                    }
                });
//                switch (index) {
//                    case 0: // first button
//
//                        Toast.makeText(getApplicationContext(), "Selected button number " + index, Toast.LENGTH_LONG).show();
//                        break;
//                    case 1: // secondbutton
//
//                        Toast.makeText(getApplicationContext(), "Selected button number " + index, Toast.LENGTH_LONG).show();
//                        break;
//                }
            }
        });

    }
}
