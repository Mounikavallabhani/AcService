package com.mansopresk.mansopresk01.acservice.electricalactivity;

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

public class ElectricService extends AppCompatActivity {
    SharedPreferences sharedpreferences;
    Button electb;
    EditText ectricaldis;
    SharedPreferences.Editor editor;
    public static final String MyPREFERENCES = "electricaldetails";
    public static final String ELECTRICALMyPREFERENCES = "electrical";
    public static final String CARCALENDERMyPREFERENCES = "calnder";
    LinearLayout  electricalll;
    EditText electricalcalender;
    Spinner spinnerelectrical;
    Calendar calendar;
    int year, month, day;
    static final int DATE_PICKER_ID = 1111;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.electricalservice);
        electb = findViewById(R.id.carpentersubmit);
        ectricaldis = findViewById(R.id.carpenter);
        electricalll=findViewById(R.id.electricalll);
        spinnerelectrical=findViewById(R.id.spinnerelectrical);
        electricalcalender=findViewById(R.id.electricalcalender);
        Toolbar mToolbar = (Toolbar)findViewById(R.id.toolbarcredits);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i2 = new Intent(getApplicationContext(),ElectricalWorks.class);
                getApplicationContext().getSharedPreferences(ELECTRICALMyPREFERENCES, 0).edit().clear().apply();
                startActivity(i2);
                //finish();
            }
        });

        SharedPreferences electricalprefs = getSharedPreferences(ELECTRICALMyPREFERENCES, MODE_PRIVATE);
        String electricalimmediate = electricalprefs.getString("electricalimmediate", null);
        String electricalschedule = electricalprefs.getString("electricalschedule", null);

        if(electricalschedule!=null){
            electricalll.setVisibility(View.VISIBLE);

        }



        electb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ectricaldis.getText().toString().isEmpty()) {
//                    ectricaldis.requestFocus();
//                    ectricaldis.setError("please provide description");
                    showSnackbar(ectricaldis, "Please enter description", 4000);

                }

                if(electricalcalender.getText().toString().isEmpty()) {
                    showSnackbar(electricalcalender, "Please select date", 4000);
                }
                else
                if (spinnerelectrical.getSelectedItemPosition() == 0) {
                    spinnerelectrical.requestFocus();
                    showSnackbar(spinnerelectrical, "Please select time slot", 4000);
                }

                else
                {
                    // find the radiobutton by returned id

                    sharedpreferences = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
                    SharedPreferences.Editor prefEditor = sharedpreferences.edit();
                    String scontent = ectricaldis.getText().toString();

                    String setcalendar=electricalcalender.getText().toString();
                    editor = getSharedPreferences("calnder", MODE_PRIVATE).edit();
                    editor.putString("date", setcalendar);

                    String spinnertext = spinnerelectrical.getSelectedItem().toString();
                    editor.putString("spinnerSelection", spinnertext);
                    editor.commit();


                    prefEditor.putString("electricaldescription", scontent);

                    prefEditor.commit();
                    SharedPreferences prefs = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
                    String restoredTextcar = prefs.getString("electricaldescription", null);


                    SharedPreferences electricalprefs = getSharedPreferences(ELECTRICALMyPREFERENCES, MODE_PRIVATE);
                    String electricalimmediate = electricalprefs.getString("electricalimmediate", null);
                    String electricalschedule = electricalprefs.getString("electricalschedule", null);

                    SharedPreferences electricalcalnderpref = getSharedPreferences(CARCALENDERMyPREFERENCES, MODE_PRIVATE);
                    String getdate = electricalcalnderpref.getString("date", null);
                    String spinnerSelection=electricalcalnderpref.getString("spinnerSelection",null);




                    String mail = "lvssrinivas@gmail.com";
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", mail, null));
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Requirement Electrical Service");
//                    emailIntent.putExtra(Intent.EXTRA_TEXT, "Required Electrical Services:\n" + restoredTextcar
//                    );

                    if (electricalimmediate!=null) {
                        emailIntent.putExtra(Intent.EXTRA_TEXT, "Required Electrical Services:\n" + restoredTextcar+","+electricalimmediate);
                    }


                    else{
                        emailIntent.putExtra(Intent.EXTRA_TEXT, "Required Electrical Services:\n" + restoredTextcar+","+
                                electricalschedule+","+getdate+","+spinnerSelection);

                    }
                    try {
                        getApplicationContext().getSharedPreferences(ELECTRICALMyPREFERENCES, 0).edit().clear().apply();
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
            electricalcalender.setText(selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear);
        }
    };

    @Override
    public void onBackPressed() {
        Intent i=new Intent(getApplicationContext(),ElectricalWorks.class);
        getApplicationContext().getSharedPreferences(ELECTRICALMyPREFERENCES, 0).edit().clear().apply();
        startActivity(i);

    }
    public void showSnackbar(View view, String message, int duration) {
        Snackbar.make(view, message, duration).show();
    }
}
