package com.mansopresk.mansopresk01.acservice.packersactivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.mansopresk.mansopresk01.acservice.MainActivity;
import com.mansopresk.mansopresk01.acservice.R;
import com.mansopresk.mansopresk01.acservice.plumbingactivity.Plumbing;

import java.util.Calendar;
import java.util.Date;

public class PackersMovers extends AppCompatActivity {
    RadioGroup radiogroupoverhead;
    RadioButton radiopackers;
    int selectedId;
    Button pandmsubmit;
    SharedPreferences sharedPreferences;
    String packers;
    EditText packerscontent;
    public static final String PACKERSMyPREFERENCES = "packersandmovers";
    public static final String PACKERSDES = "Packersdetails";

    LinearLayout mPackers_Schedule_layout;
    Calendar calendar;
    int year, month, day;
    static final int DATE_PICKER_ID = 1111;
    EditText  mPackers_Calender_EditText;
    Spinner mPackers_Spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packers_movers);


        mPackers_Schedule_layout =findViewById(R.id.packers_schedule_layout);
        mPackers_Calender_EditText = findViewById(R.id.edit_text_Packers_calender);
        mPackers_Spinner =findViewById(R.id.packers_spinner);

        Toolbar mToolbar = (Toolbar)findViewById(R.id.toolbarcredits);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i2 = new Intent(getApplicationContext(),PackerMoversService.class);
                startActivity(i2);
                //finish();
            }
        });
        pandmsubmit=findViewById(R.id.pandmsubmit);
        packerscontent=findViewById(R.id.packerscontent);

        SharedPreferences dessprefs = getSharedPreferences(PACKERSDES, MODE_PRIVATE);
        String spackersoutput = dessprefs.getString("plumbingschedule", null);

        if (spackersoutput!=null)
        {
            mPackers_Schedule_layout.setVisibility(View.VISIBLE);
        }


        radiogroupoverhead =  (RadioGroup) findViewById(R.id.radiogroupoverhead);
        //get selected radio button from radioGroup
        // find the radio button by returned id
        radiopackers = (RadioButton) findViewById(selectedId);

        sharedPreferences = getSharedPreferences("Packersdetails",MODE_PRIVATE);
        radiogroupoverhead.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                selectedId = mehneedgrp .getCheckedRadioButtonId();
//                mehneedbtn = (RadioButton) findViewById(selectedId);
                final View radioButton = radiogroupoverhead.findViewById(checkedId);
                selectedId = radiogroupoverhead.indexOfChild(radioButton);
                //mehneedbtn = (RadioButton) findViewById(selectedId);

                radioButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int selectdataId = radiogroupoverhead .getCheckedRadioButtonId();
                        radiopackers=findViewById(selectdataId);
                        //Intent i=new Intent(MehandiHireActivity.this,MehendiCostsActivity.class);
                        packers=radiopackers.getText().toString();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("packersoutput",packers);
                        editor.apply();
                        editor.commit();
                        //startActivity(i);
                    }
                });


            }
        });

        pandmsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(packerscontent.getText().toString().isEmpty())
                {
                    showSnackbar(packerscontent, "provide description", 4000);
                }else  if(radiogroupoverhead.getCheckedRadioButtonId()==-1)
                {
                    Toast.makeText(getApplicationContext(), "Please select whichtype home", Toast.LENGTH_SHORT).show();
                }
                else
                if(mPackers_Calender_EditText.getText().toString().isEmpty()) {
                    showSnackbar(mPackers_Calender_EditText, "Please select date", 4000);
                }
                else
                if (mPackers_Spinner.getSelectedItemPosition() == 0) {
                    mPackers_Spinner.requestFocus();
                    showSnackbar(mPackers_Spinner, "Please select time slot", 4000);
                }
                else{
                    sharedPreferences = getSharedPreferences("Packersdetails",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    String spackerscontent=packerscontent.getText().toString();
                    editor.putString("packerdescription",spackerscontent);
                    editor.commit();



                    SharedPreferences packersprefs = getSharedPreferences(PACKERSMyPREFERENCES, MODE_PRIVATE);
                    String packersimmediate = packersprefs.getString("packersimmediate", null);
                    String pekersschedule = packersprefs.getString("pekersschedule", null);


                    SharedPreferences dessprefs = getSharedPreferences(PACKERSDES, MODE_PRIVATE);
                    String spackerdescription = dessprefs.getString("packerdescription", null);
                    String spackersoutput = dessprefs.getString("packersoutput", null);


                    SharedPreferences plumbingcalnderpref = getSharedPreferences("calnder", MODE_PRIVATE);
                    String getdate = plumbingcalnderpref.getString("date", null);
                    String spinnerSelection=plumbingcalnderpref.getString("spinnerSelection",null);




                    String mail = "lvssrinivas@gmail.com";
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", mail, null));
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Requirement of plumbing Service");


                    if (packersimmediate!=null) {
                        emailIntent.putExtra(Intent.EXTRA_TEXT, "Required plumbing Services:\n" + packersimmediate+","+spackerdescription
                       +","+ spackersoutput);

                    }

                    else{
                        emailIntent.putExtra(Intent.EXTRA_TEXT, "Required plumbing Services:\n" + pekersschedule+","+
                                spackerdescription+","+getdate+","+spinnerSelection+","+spackersoutput);

                    }
                    try {
                        getApplicationContext().getSharedPreferences(PACKERSDES, 0).edit().clear().apply();
                        startActivity(emailIntent);

                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(getApplicationContext(), "Mail account not configured", Toast.LENGTH_LONG).show();
                    }
                    emailIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               }



                }


        });


    }

    public void carcalender(View v) {
        calendar = Calendar.getInstance();
        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);
        showDialog(DATE_PICKER_ID);
    }
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_PICKER_ID:
                DatePickerDialog datePickerDialog = new DatePickerDialog(this, R.style.DialogTheme,datePickerListener, year, month, day);
                calendar = Calendar.getInstance();
                calendar.add(Calendar.DATE, 0); // Add 0 days to Calendar
                Date newDate = calendar.getTime();
                datePickerDialog.getDatePicker().setMinDate(newDate.getTime() - (newDate.getTime() % (24 * 60 * 60 * 1000)));
                return datePickerDialog;
        }
        return null;
    }
    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        // the callback received when the user "sets" the Date in the
        // DatePickerDialog
        public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
            mPackers_Calender_EditText.setText(selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear);
        }
    };

    @Override
    public void onBackPressed() {
        Intent i=new Intent(getApplicationContext(),PackerMoversService.class);
        getApplicationContext().getSharedPreferences(PACKERSMyPREFERENCES, 0).edit().clear().apply();
        startActivity(i);

    }
    public void showSnackbar(View view, String message, int duration) {
        Snackbar.make(view, message, duration).show();
    }
}
