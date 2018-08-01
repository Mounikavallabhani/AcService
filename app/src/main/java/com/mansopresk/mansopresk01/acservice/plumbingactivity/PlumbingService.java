package com.mansopresk.mansopresk01.acservice.plumbingactivity;

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
import android.widget.Spinner;
import android.widget.Toast;

import com.mansopresk.mansopresk01.acservice.R;
import com.mansopresk.mansopresk01.acservice.caractivity.CarBikeRepair;

import java.util.Calendar;
import java.util.Date;

public class PlumbingService extends AppCompatActivity {
    SharedPreferences sharedpreferences, plumbingprefs;
    Button plumbingb;
    EditText plumbingdis;
    SharedPreferences.Editor editor;
    public static final String MyPREFERENCES = "plumbingdetails";
    public static final String PLUMBINGMyPREFERENCES = "plumbing";

    LinearLayout mPlumbing_Schedule_layout;
    Calendar calendar;
    int year, month, day;
    static final int DATE_PICKER_ID = 1111;
    EditText  mPlumbing_Calender_EditText;
    Spinner mPlumbing_Spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plumbing_service);

        mPlumbing_Schedule_layout =findViewById(R.id.plumbing_schedule_layout);
        plumbingb = findViewById(R.id.carpentersubmit);
        plumbingdis= findViewById(R.id.carpenter);
        mPlumbing_Calender_EditText = findViewById(R.id.edit_text_Plumbing_calender);
        mPlumbing_Spinner =findViewById(R.id.plumbing_spinner);

        Toolbar mToolbar = (Toolbar)findViewById(R.id.toolbarcredits);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i2 = new Intent(getApplicationContext(),Plumbing.class);
                getApplicationContext().getSharedPreferences(PLUMBINGMyPREFERENCES, 0).edit().clear().apply();
                startActivity(i2);
                //finish();
            }
        });


        plumbingprefs = getSharedPreferences(PLUMBINGMyPREFERENCES, MODE_PRIVATE);
        String plumbingschedule = plumbingprefs.getString("plumbingschedule", null);

        if (plumbingschedule!=null)
        {
            mPlumbing_Schedule_layout.setVisibility(View.VISIBLE);
        }


        plumbingb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (plumbingdis.getText().toString().isEmpty()) {
                    showSnackbar(plumbingdis, "Please enter description", 4000);

                }
                else
                if(mPlumbing_Calender_EditText.getText().toString().isEmpty()) {
                    showSnackbar(mPlumbing_Calender_EditText, "Please select date", 4000);
                }
                else
                if (mPlumbing_Spinner.getSelectedItemPosition() == 0) {
                    mPlumbing_Spinner.requestFocus();
                    showSnackbar(mPlumbing_Spinner, "Please select time slot", 4000);
                }
                else
                {


                    // find the radiobutton by returned id

                    sharedpreferences = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
                    SharedPreferences.Editor prefEditor = sharedpreferences.edit();
                    String scontent = plumbingdis.getText().toString();

                    prefEditor.putString("plumbingdescription", scontent);

                    prefEditor.commit();


                    String setcalendar=mPlumbing_Calender_EditText.getText().toString();
                    editor = getSharedPreferences("calnder", MODE_PRIVATE).edit();
                    editor.putString("date", setcalendar);

                    String spinnertext = mPlumbing_Spinner.getSelectedItem().toString();
                    editor.putString("spinnerSelection", spinnertext);
                    editor.commit();

                    SharedPreferences prefs = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
                    String restoredTextcar = prefs.getString("plumbingdescription", null);

                    plumbingprefs = getSharedPreferences(PLUMBINGMyPREFERENCES, MODE_PRIVATE);
                    String plumbingimmediate = plumbingprefs.getString("plumbingimmediate", null);
                    String plumbingschedule = plumbingprefs.getString("plumbingschedule", null);

                    SharedPreferences plumbingcalnderpref = getSharedPreferences("calnder", MODE_PRIVATE);
                    String getdate = plumbingcalnderpref.getString("date", null);
                    String spinnerSelection=plumbingcalnderpref.getString("spinnerSelection",null);



                    String mail = "lvssrinivas@gmail.com";
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", mail, null));
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Requirement of plumbing Service");
//                    emailIntent.putExtra(Intent.EXTRA_TEXT, "Required plumbing Services:\n" + restoredTextcar
//                    );

                    if (plumbingimmediate!=null) {
                        emailIntent.putExtra(Intent.EXTRA_TEXT, "Required plumbing Services:\n" + restoredTextcar+","+plumbingimmediate
                        );

                    }

                    else{
                        emailIntent.putExtra(Intent.EXTRA_TEXT, "Required plumbing Services:\n" + restoredTextcar+","+
                                plumbingschedule+","+getdate+","+spinnerSelection
                        );

                    }
                    try {
                        getApplicationContext().getSharedPreferences(PLUMBINGMyPREFERENCES, 0).edit().clear().apply();
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
            mPlumbing_Calender_EditText.setText(selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear);
        }
    };

    @Override
    public void onBackPressed() {
        Intent i=new Intent(getApplicationContext(),Plumbing.class);
        getApplicationContext().getSharedPreferences(PLUMBINGMyPREFERENCES, 0).edit().clear().apply();
        startActivity(i);

    }
    public void showSnackbar(View view, String message, int duration) {
        Snackbar.make(view, message, duration).show();
    }
}
