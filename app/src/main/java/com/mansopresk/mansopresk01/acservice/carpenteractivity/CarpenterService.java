package com.mansopresk.mansopresk01.acservice.carpenteractivity;

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

public class CarpenterService extends AppCompatActivity {
    SharedPreferences sharedpreferences;
    Button carpentb;
    EditText carpenterdis,carpenteretcalender;
    SharedPreferences.Editor editor;
    public static final String MyPREFERENCES = "carpenterdetails";
    public static final String CARPENTERMyPREFERENCES = "carpenter";
    public static final String CARCALENDERMyPREFERENCES = "calnder";
    Intent emailIntent;
    LinearLayout carpenterll;
    Spinner spinnercarpenter;
    int year, month, day;
    Calendar calendar;
    static final int DATE_PICKER_ID = 1111;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carpenter_service);
        carpentb = findViewById(R.id.carpentersubmit);
        carpenterdis = findViewById(R.id.carpenter);
        carpenteretcalender=findViewById(R.id.carpenteretcalender);
        spinnercarpenter=findViewById(R.id.spinnercarpenter);


        carpenterll=findViewById(R.id.carpenterll);
        Toolbar mToolbar = (Toolbar)findViewById(R.id.toolbarcredits);
//        mToolbar.setTitle("Mansopresk");


        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i2 = new Intent(getApplicationContext(),Carpenter.class);
                getApplicationContext().getSharedPreferences(CARPENTERMyPREFERENCES, 0).edit().clear().apply();
                startActivity(i2);
                //finish();
            }
        });

        SharedPreferences carpenterprefs = getSharedPreferences(CARPENTERMyPREFERENCES, MODE_PRIVATE);
        String getcarpenterimmediate = carpenterprefs.getString("carpenterimmediate", null);
        String getcarpenterschedule = carpenterprefs.getString("carpenterschedule", null);
        if(getcarpenterschedule!=null){
            carpenterll.setVisibility(View.VISIBLE);

        }


        carpentb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (carpenterdis.getText().toString().isEmpty()) {
                    showSnackbar(carpenterdis, "Please enter description", 4000);

                }
                else
                if(carpenteretcalender.getText().toString().isEmpty()) {
                    showSnackbar(carpenteretcalender, "Please select date", 4000);
                }
                else
                if (spinnercarpenter.getSelectedItemPosition() == 0) {
                    spinnercarpenter.requestFocus();
                    showSnackbar(spinnercarpenter, "Please select time slot", 4000);
                }else {


                    // find the radiobutton by returned id

                    sharedpreferences = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
                    SharedPreferences.Editor prefEditor = sharedpreferences.edit();
                    String scontent = carpenterdis.getText().toString();

                    prefEditor.putString("carpenterdescription", scontent);

                    prefEditor.commit();
                    SharedPreferences prefs = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
                    String restoredTextcar = prefs.getString("carpenterdescription", null);

                    SharedPreferences carpenterprefs = getSharedPreferences(CARPENTERMyPREFERENCES, MODE_PRIVATE);
                    String getcarpenterimmediate = carpenterprefs.getString("carpenterimmediate", null);
                    String getcarpenterschedule = carpenterprefs.getString("carpenterschedule", null);


                    SharedPreferences carcalnderpref = getSharedPreferences(CARCALENDERMyPREFERENCES, MODE_PRIVATE);
                    String getdate = carcalnderpref.getString("date", null);
                    String spinnerSelection=carcalnderpref.getString("spinnerSelection",null);

                    String mail = "lvssrinivas@gmail.com";
                    emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", mail, null));
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Requirement of carpenter Service");
                    if (getcarpenterimmediate!=null) {
                        emailIntent.putExtra(Intent.EXTRA_TEXT, "Required carpenter Services:\n" + restoredTextcar + "," + getcarpenterimmediate);
                    }

                    else{
                        emailIntent.putExtra(Intent.EXTRA_TEXT, "Required carpenter Services:\n" + restoredTextcar + "" +
                                getcarpenterschedule+","+getdate+","+spinnerSelection);
              }
                    try {
                        getApplicationContext().getSharedPreferences(CARPENTERMyPREFERENCES, 0).edit().clear().apply();
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
            carpenteretcalender.setText(selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear);
        }
    };

    @Override
    public void onBackPressed() {
        Intent i=new Intent(getApplicationContext(),Carpenter.class);
        getApplicationContext().getSharedPreferences(CARPENTERMyPREFERENCES, 0).edit().clear().apply();
        startActivity(i);

    }


    public void showSnackbar(View view, String message, int duration) {
        Snackbar.make(view, message, duration).show();
    }
}
