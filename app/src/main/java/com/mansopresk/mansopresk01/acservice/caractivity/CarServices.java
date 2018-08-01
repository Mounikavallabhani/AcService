package com.mansopresk.mansopresk01.acservice.caractivity;

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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CarServices extends AppCompatActivity {
    RadioButton gbutton;
    RadioGroup gcount;
    int selectedId;
    String strmehgcount;
    Button carsubmit;
    EditText content;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    public static final String MyPREFERENCES = "carbikedetails";
    public static final String CARMyPREFERENCES = "carrepair";
    public static final String CARCALENDERMyPREFERENCES = "calnder";
    Calendar calendar;
     int year, month, day;
    static final int DATE_PICKER_ID = 1111;
    EditText etcalendar;
    Button calnderok;
    Spinner spinner;
    LinearLayout carll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_services);
        radioGroup = (RadioGroup) findViewById(R.id.cargroup);
        etcalendar=findViewById(R.id.etcarcalender);
        carsubmit = findViewById(R.id.carsubmit);
        content = findViewById(R.id.content);
        spinner=findViewById(R.id.spinnercar);
        Toolbar mToolbar = (Toolbar)findViewById(R.id.toolbarcredits);
        carll=findViewById(R.id.carll);
//        mToolbar.setTitle("Mansopresk");
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i2 = new Intent(getApplicationContext(),CarBikeRepair.class);
                getApplicationContext().getSharedPreferences(CARMyPREFERENCES, 0).edit().clear().apply();
                startActivity(i2);
                //finish();
            }
        });
        SharedPreferences carpref = getSharedPreferences(CARMyPREFERENCES, MODE_PRIVATE);
        //String getcarimmediate = carpref.getString("carimmediate", null);
        final String getcarschedule=carpref.getString("carschedule",null);
        if(getcarschedule!=null){
           carll.setVisibility(View.VISIBLE);

        }



        carsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (content.getText().toString().isEmpty()) {
                    showSnackbar(content, "Please enter description", 4000);

                } else  if(radioGroup.getCheckedRadioButtonId()==-1)
                {
                    Toast.makeText(getApplicationContext(), "Please select whichtype repair", Toast.LENGTH_SHORT).show();
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

                else{

                    int selectedId = radioGroup.getCheckedRadioButtonId();

                    // find the radiobutton by returned id
                    radioButton = (RadioButton) findViewById(selectedId);
                    sharedpreferences = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
                    SharedPreferences.Editor prefEditor = sharedpreferences.edit();
                    String scontent = content.getText().toString();
                    String sradioButton=radioButton.getText().toString();
                    prefEditor.putString("cardescription", scontent);
                    prefEditor.putString("radiooption", sradioButton);
                    prefEditor.commit();


                       String setcalendar=etcalendar.getText().toString();
                       editor = getSharedPreferences("calnder", MODE_PRIVATE).edit();
                       editor.putString("date", setcalendar);

                       String spinnertext = spinner.getSelectedItem().toString();
                       editor.putString("spinnerSelection", spinnertext);
                       editor.commit();

                    SharedPreferences prefs = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
                    String restoredTextcar = prefs.getString("cardescription", null);
                    String radiooptiontext=prefs.getString("radiooption",null);


                    SharedPreferences carpref = getSharedPreferences(CARMyPREFERENCES, MODE_PRIVATE);
                    String getcarimmediate = carpref.getString("carimmediate", null);
                    String getcarschedule=carpref.getString("carschedule",null);

                    SharedPreferences carcalnderpref = getSharedPreferences(CARCALENDERMyPREFERENCES, MODE_PRIVATE);
                    String getdate = carcalnderpref.getString("date", null);
                   String spinnerSelection=carcalnderpref.getString("spinnerSelection",null);
                    String mail = "lvssrinivas@gmail.com";
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", mail, null));
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Requirement of car Service");


                    if (getcarimmediate!=null) {
                        emailIntent.putExtra(Intent.EXTRA_TEXT, "Required car Services:\n" + radiooptiontext + "," + restoredTextcar+","+getcarimmediate);
                    }


                    else{
                        emailIntent.putExtra(Intent.EXTRA_TEXT, "Required car Services:\n" + radiooptiontext + "," +
                                restoredTextcar+","+getcarschedule+","+getdate+","+spinnerSelection);
                    }
                    try {
                        getApplicationContext().getSharedPreferences(CARMyPREFERENCES, 0).edit().clear().apply();
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
        Intent i=new Intent(getApplicationContext(),CarBikeRepair.class);
        getApplicationContext().getSharedPreferences(CARMyPREFERENCES, 0).edit().clear().apply();
        startActivity(i);

    }

    public void showSnackbar(View view, String message, int duration) {
        Snackbar.make(view, message, duration).show();
    }
}
