package com.mansopresk.mansopresk01.acservice;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.mansopresk.mansopresk01.acservice.caractivity.CarServices;
import com.mansopresk.mansopresk01.acservice.cleaningactivity.CleaningService;

import java.util.Calendar;
import java.util.Date;

public class AcCalnder extends AppCompatActivity {
    Calendar calendar;
    private int year, month, day;
    static final int DATE_PICKER_ID = 1111;
    EditText etcalendar;
    Button calnderok;
    Spinner spinner;
    SharedPreferences.Editor editor;
    SharedPreferences sharedpreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ac_calnder);
        etcalendar = findViewById(R.id.etcalender);
        calnderok=findViewById(R.id.calnderok);
        spinner=findViewById(R.id.spinner);


        Toolbar mToolbar = (Toolbar)findViewById(R.id.toolbarcredits);
//        mToolbar.setTitle("Mansopresk");
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i2 = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i2);
                //finish();
            }
        });


        sharedpreferences = getSharedPreferences("carrepair", MODE_PRIVATE);
        sharedpreferences = getSharedPreferences("cleaning", MODE_PRIVATE);


        calnderok.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String cleaningschedule = sharedpreferences.getString("cleaningschedule", null);
               String carschedule = sharedpreferences.getString("carschedule", null);
               String setcalendar=etcalendar.getText().toString();
               editor = getSharedPreferences("calnder", MODE_PRIVATE).edit();
               editor.putString("date", setcalendar);

               String spinnertext = spinner.getSelectedItem().toString();
               editor.putString("spinnerSelection", spinnertext);
               editor.commit();
               if(etcalendar.getText().toString().isEmpty()) {
                   showSnackbar(spinner, "Please select date", 4000);
               }
               else if (spinner.getSelectedItemPosition() == 0) {
                   spinner.requestFocus();
                   showSnackbar(spinner, "Please select time slot", 4000);
               }

                   else if(carschedule!=null) {

                       Intent it = new Intent(getApplicationContext(), CarServices.class);

                       startActivity(it);

                   }else if(cleaningschedule!=null)
                       {

                              Intent it = new Intent(getApplicationContext(), CleaningService.class);


                           startActivity(it);

                       }
                       else
               {
                   getApplicationContext().getSharedPreferences("carrepair", 0).edit().clear().apply();
               }




           }
       });
    }
        public void calender(View v) {
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
        public void onBackPressed() {
            super.onBackPressed();


        }
    public void showSnackbar(View view, String message, int duration) {
        Snackbar.make(view, message, duration).show();
    }


    }

