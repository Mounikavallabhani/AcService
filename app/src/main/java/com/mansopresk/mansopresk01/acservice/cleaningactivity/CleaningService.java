package com.mansopresk.mansopresk01.acservice.cleaningactivity;

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

import com.mansopresk.mansopresk01.acservice.R;
import com.mansopresk.mansopresk01.acservice.caractivity.CarBikeRepair;

import java.util.Calendar;
import java.util.Date;

public class CleaningService extends AppCompatActivity {
    Button cleaningrsubmit;
    EditText cleaningdescription;
    RadioGroup radiogroupoverhead;
    LinearLayout cleanll;
    EditText etcalendar;
    Spinner spinner;
    Calendar calendar;
    SharedPreferences.Editor editor;
    int year, month, day;
    static final int DATE_PICKER_ID = 1111;
    public static final String CLEANINGMyPREFERENCES = "cleaning";
    public static final String CMyPREFERENCES = "cleaningdetails";
    public static final String CARCALENDERMyPREFERENCES = "calnder";
    RadioButton radioButtoncleaning;
    SharedPreferences sharedpreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cleaning_service);
        cleaningrsubmit=findViewById(R.id.cleaningrsubmit);
        cleaningdescription=findViewById(R.id.cleaningdescription);
        cleanll=findViewById(R.id.cleanll);

        etcalendar=findViewById(R.id.etcleaningcalender);
        spinner=findViewById(R.id.spinnercleaning);
        Toolbar mToolbar = (Toolbar)findViewById(R.id.toolbarcredits);
        radiogroupoverhead=findViewById(R.id.radiogroupoverhead);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i2 = new Intent(getApplicationContext(),Cleaning.class);
                getApplicationContext().getSharedPreferences(CLEANINGMyPREFERENCES, 0).edit().clear().apply();
                startActivity(i2);
                //finish();
            }
        });
        SharedPreferences cleaningpref = getSharedPreferences(CLEANINGMyPREFERENCES, MODE_PRIVATE);
        //String getcarimmediate = carpref.getString("carimmediate", null);
        final String getcleaningschedule=cleaningpref.getString("cleaningschedule",null);
        if(getcleaningschedule!=null){
            cleanll.setVisibility(View.VISIBLE);

        }



        cleaningrsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cleaningdescription.getText().toString().isEmpty()) {
                    showSnackbar(cleaningdescription, "Please enter description", 4000);

                }else  if(radiogroupoverhead.getCheckedRadioButtonId()==-1)
                {
                    Toast.makeText(getApplicationContext(), "Please select whichtype home", Toast.LENGTH_SHORT).show();
                }
                else
                if(etcalendar.getText().toString().isEmpty()) {
                    showSnackbar(etcalendar, "Please select date", 4000);
                }
                else
                if (spinner.getSelectedItemPosition() == 0) {
                    spinner.requestFocus();
                    showSnackbar(spinner, "Please select time slot", 4000);
                }

                else {
                    int selectedId = radiogroupoverhead.getCheckedRadioButtonId();

                    // find the radiobutton by returned id
                    radioButtoncleaning = (RadioButton) findViewById(selectedId);
                    sharedpreferences = getSharedPreferences(CMyPREFERENCES, MODE_PRIVATE);
                    SharedPreferences.Editor prefEditor = sharedpreferences.edit();
                    String scontent = cleaningdescription.getText().toString();
                    String sradioButton=radioButtoncleaning.getText().toString();
                    prefEditor.putString("cleaningdescription", scontent);
                    prefEditor.putString("cleaningradiooption", sradioButton);
                    prefEditor.commit();


                    String setcalendar=etcalendar.getText().toString();
                    editor = getSharedPreferences("calnder", MODE_PRIVATE).edit();
                    editor.putString("date", setcalendar);

                    String spinnertext = spinner.getSelectedItem().toString();
                    editor.putString("spinnerSelection", spinnertext);
                    editor.commit();



                    SharedPreferences prefs = getSharedPreferences(CMyPREFERENCES, MODE_PRIVATE);
                    String cleaningdescription = prefs.getString("cleaningdescription", null);
                    String cleaningradiooption=prefs.getString("cleaningradiooption",null);


                    SharedPreferences cleaningpref = getSharedPreferences(CLEANINGMyPREFERENCES, MODE_PRIVATE);
                    String cleaningimmediate = cleaningpref.getString("cleaningimmediate", null);
                    String cleaningschedule=cleaningpref.getString("cleaningschedule",null);



                    SharedPreferences carcalnderpref = getSharedPreferences(CARCALENDERMyPREFERENCES, MODE_PRIVATE);
                    String getdate = carcalnderpref.getString("date", null);
                    String spinnerSelection=carcalnderpref.getString("spinnerSelection",null);
                    String mail = "lvssrinivas@gmail.com";
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", mail, null));
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Requirement of car Service");


                    if (cleaningimmediate!=null) {
                        emailIntent.putExtra(Intent.EXTRA_TEXT, "Required car Services:\n" + cleaningdescription + "," +
                                cleaningradiooption+","+cleaningimmediate);
                    }


                    else{
                        emailIntent.putExtra(Intent.EXTRA_TEXT, "Required car Services:\n" + cleaningdescription + "," +
                                cleaningradiooption+","+cleaningschedule+","+getdate+","+spinnerSelection);
                    }
                    try {
                        getApplicationContext().getSharedPreferences(CMyPREFERENCES, 0).edit().clear().apply();
                        getApplicationContext().getSharedPreferences(CARCALENDERMyPREFERENCES,0).edit().clear().apply();
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
            etcalendar.setText(selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear);
        }
    };

    @Override
    public void onBackPressed() {
        Intent i=new Intent(getApplicationContext(),Cleaning.class);
        getApplicationContext().getSharedPreferences(CLEANINGMyPREFERENCES, 0).edit().clear().apply();
        startActivity(i);

    }
    public void showSnackbar(View view, String message, int duration) {
        Snackbar.make(view, message, duration).show();
    }
}
