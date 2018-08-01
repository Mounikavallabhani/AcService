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


public class MehendiCostsActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    RadioButton mehcostrb,bridetrd,bridethd;
    String strmehcost;
    RadioGroup mehcostrg;
    int selectedId;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mehendi_costs);

        Toolbar mToolbar = (Toolbar)findViewById(R.id.toolbarcredits);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i2 = new Intent(getApplicationContext(),MehendiRequireForActivity.class);
                startActivity(i2);
                //finish();
            }
        });

//              sharedPreferences   = getSharedPreferences("mehendidetails", MODE_PRIVATE);
//        String restoredText = sharedPreferences.getString("mehreqforoutput", null);
//        Toast.makeText(this, restoredText, Toast.LENGTH_SHORT).show();
        bridetrd=findViewById(R.id.bridetrd);
        bridethd=findViewById(R.id.bridethd);
//        guestthd=findViewById(R.id.gueststhd);
//        guesttrd=findViewById(R.id.gueststrd);
//        bothtrd=findViewById(R.id.bothtrd);
//        boththd=findViewById(R.id.boththd);

//        bridetrd.setOnClickListener(this);
//        bridethd.setOnClickListener(this);
//        guestthd.setOnClickListener(this);
//        guesttrd.setOnClickListener(this);
//        bothtrd.setOnClickListener(this);
//        boththd.setOnClickListener(this);
        mehcostrg =  (RadioGroup) findViewById(R.id.mehcostrg);
        //get selected radio button from radioGroup
        // find the radio button by returned id
        mehcostrb = (RadioButton) findViewById(selectedId);
        sharedPreferences = getSharedPreferences("mehendidetails",MODE_PRIVATE);
        editor = sharedPreferences.edit();





        //  sharedPreferences = getSharedPreferences("mehendidetails",MODE_PRIVATE);

        mehcostrg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, final int checkedId) {
//                selectedId = mehneedgrp .getCheckedRadioButtonId();
//                mehneedbtn = (RadioButton) findViewById(selectedId);
                final View radioButton = mehcostrg.findViewById(checkedId);
                selectedId = mehcostrg.indexOfChild(radioButton);
                //mehneedbtn = (RadioButton) findViewById(selectedId);

                radioButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int selectdataId = mehcostrg .getCheckedRadioButtonId();
                        mehcostrb=findViewById(selectdataId);
                        if(checkedId==R.id.bridetrd || checkedId==R.id.bridethd) {
                            Intent i = new Intent(MehendiCostsActivity.this, MehandiHireActivity.class);
                            strmehcost=mehcostrb.getText().toString();
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("mehcostoutput",strmehcost);
                            editor.apply();
                            startActivity(i);
                        }else {
                            Intent intent = new Intent(MehendiCostsActivity.this, MehendiGuestsCountActivity.class);
                            strmehcost=mehcostrb.getText().toString();
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("mehcostoutput",strmehcost);
                            editor.apply();
                            startActivity(intent);
                        }

                        //startActivity(i);
                    }
                });

            }
        });
    }
}

//    @Override
//    public void onClick(View v) {
//        switch(v.getId()) {
//            case R.id.bridetrd:
//                Intent btrd=new Intent(this,MehandiHireActivity.class);
//                strmehcost=bridetrd.getText().toString();
//                editor.putString("mehcostoutput",strmehcost);
//                startActivity(btrd);
//                break;
//            case R.id.bridethd:
//                Intent bthd=new Intent(this,MehandiHireActivity.class);
//                strmehcost=bridethd.getText().toString();
//                editor.putString("mehcostoutput",strmehcost);
//                startActivity(bthd);
//                break;
//            case R.id.gueststhd:
//                Intent gthd=new Intent(this,MehendiGuestsCountActivity.class);
//                strmehcost=guestthd.getText().toString();
//                editor.putString("mehcostoutput",strmehcost);
//                startActivity(gthd);
//                break;
//            case R.id.gueststrd:
//                Intent gtrd=new Intent(this,MehendiGuestsCountActivity.class);
//                strmehcost=guesttrd.getText().toString();
//                editor.putString("mehcostoutput",strmehcost);
//                startActivity(gtrd);
//                break;
//            case R.id.bothtrd:
//                Intent mbothtrd=new Intent(this,MehendiGuestsCountActivity.class);
//                strmehcost=bothtrd.getText().toString();
//                editor.putString("mehcostoutput",strmehcost);
//                startActivity(mbothtrd);
//                break;
//            case R.id.boththd:
//                Intent mboththd=new Intent(this,MehendiGuestsCountActivity.class);
//                strmehcost=boththd.getText().toString();
//                editor.putString("mehcostoutput",strmehcost);
//                startActivity(mboththd);
//                break;
//
//        }
//  }

