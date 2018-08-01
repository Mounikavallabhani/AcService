package com.mansopresk.mansopresk01.acservice.acactivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;


import com.mansopresk.mansopresk01.acservice.R;
import com.mansopresk.mansopresk01.acservice.caractivity.CarBikeRepair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class ImmediateService extends AppCompatActivity {
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    Button submitbtn;
    ArrayList<String> selectedItems = new ArrayList<String>();
    ListView myList;
    public static final String MyPREFERENCES = "MyUserChoice" ;
    public static final String ACPREFERENCES = "acservice" ;
    public static final String CARCALENDERMyPREFERENCES = "calnder";
    RadioGroup acrg;
    String actype,acdesstr;
    RadioButton acrb;
    SharedPreferences.Editor prefEditor;
    EditText acContent,accalender;
    int selectedId;
    Spinner spinnerac;
    LinearLayout acll;
    Calendar calendar;
    int year, month, day;
    View row;
    static final int DATE_PICKER_ID = 1111;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_immediate_service);
        submitbtn=findViewById(R.id.submit);
        myList = (ListView)findViewById(R.id.list);
        acrg =  (RadioGroup) findViewById(R.id.acrg);
        acContent=findViewById(R.id.acdesc);
        spinnerac=findViewById(R.id.spinnerac);
        acll=findViewById(R.id.acll);
        sharedpreferences = getSharedPreferences(MyPREFERENCES,MODE_PRIVATE);
       // prefEditor = sharedpreferences.edit();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, getResources().getStringArray(R.array.Mobile_OS));

        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.activity_checkbox, getResources().getStringArray(R.array.Mobile_OS));
        myList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
      // myList.setSelector(R.color.black);
        myList.setAdapter(adapter);
        accalender=findViewById(R.id.accalender);
        Toolbar mToolbar = (Toolbar)findViewById(R.id.toolbarcredits);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i2 = new Intent(getApplicationContext(),AcService.class);
                getApplicationContext().getSharedPreferences(ACPREFERENCES, 0).edit().clear().apply();
                startActivity(i2);
                //finish();
            }
        });

        SharedPreferences acprefs = getSharedPreferences(ACPREFERENCES, MODE_PRIVATE);
        String acimmediate = acprefs.getString("acimmediate", null);
        String acschedule = acprefs.getString("acschedule", null);
        if(acschedule!=null){
            acll.setVisibility(View.VISIBLE);

        }


        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectedId = acrg.getCheckedRadioButtonId();
                acrb = (RadioButton) findViewById(selectedId);
                actype = acrb.getText().toString();
                acdesstr = acContent.getText().toString();
                sharedpreferences = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
                SharedPreferences.Editor prefEditor = sharedpreferences.edit();
                prefEditor.putString("actypeOutput", actype);
                prefEditor.putString("acDescOutput", acdesstr);
                prefEditor.commit();

                String selected = "";
                int countChoice = myList.getCount();
                SparseBooleanArray sparseBooleanArray = myList.getCheckedItemPositions();
                //int flag=0;
                for (int i = 0; i < countChoice; i++) {

                    if (sparseBooleanArray.get(i)) {


                       selected += myList.getItemAtPosition(i).toString() + "\n";

                        SaveSelections();

                    }

                }
                if (selected == null || selected == "") {
                    Toast.makeText(ImmediateService.this, "Please check atleast on checkbox", Toast.LENGTH_SHORT).show();
                }else if(acdesstr.equals("")||acdesstr.equals(null)){
                    Toast.makeText(ImmediateService.this, "Write Description", Toast.LENGTH_SHORT).show();

                } else
                if(accalender.getText().toString().isEmpty()) {
                    showSnackbar(accalender, "Please select date", 4000);
                }
                else
                if (spinnerac.getSelectedItemPosition() == 0) {
                    spinnerac.requestFocus();
                    showSnackbar(spinnerac, "Please select time slot", 4000);
                } else {


                    //Toast.makeText(ImmediateService.this, selected, Toast.LENGTH_LONG).show();
                    SharedPreferences prefs = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
                    String restoredText = prefs.getString("immserviceOutput", null);
                    String actype = prefs.getString("actypeOutput", null);

                    SharedPreferences acprefs = getSharedPreferences(ACPREFERENCES, MODE_PRIVATE);
                    String acimmediate = acprefs.getString("acimmediate", null);
                    String acschedule = acprefs.getString("acschedule", null);

                    SharedPreferences carcalnderpref = getSharedPreferences(CARCALENDERMyPREFERENCES, MODE_PRIVATE);
                    String getdate = carcalnderpref.getString("date", null);
                    String spinnerSelection=carcalnderpref.getString("spinnerSelection",null);


                    String mail = "lvssrinivas@gmail.com";
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", mail, null));
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Requirement of Ac Service");
                    emailIntent.putExtra(Intent.EXTRA_TEXT, "Required Ac Services:\n" + actype + "," + restoredText + "," + acdesstr);

                    if (acimmediate!=null) {
                        emailIntent.putExtra(Intent.EXTRA_TEXT, "Required Ac Services:\n" + actype
                                + "," + restoredText + "," + acdesstr+"'"+acimmediate);
                    }


                    else{
                        emailIntent.putExtra(Intent.EXTRA_TEXT, "Required Ac Services:\n" + actype
                                + "," + restoredText + "," + acdesstr+"'"+acschedule+","+getdate+","+spinnerSelection);

                    }
                    try {
                        getApplicationContext().getSharedPreferences(ACPREFERENCES, 0).edit().clear().apply();
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
    private void SaveSelections() {
        SharedPreferences.Editor prefEditor = sharedpreferences.edit();
        String savedItems = getSavedItems();
        prefEditor.putString("immserviceOutput", savedItems);
        prefEditor.commit();
      }

    private String getSavedItems() {

        String savedItems = "";

        int count = this.myList.getAdapter().getCount();

        for (int i = 0; i < count; i++) {

            if (this.myList.isItemChecked(i)) {

                if (savedItems.length() > 0) {

                    savedItems += "\n" + this.myList.getItemAtPosition(i);

                } else {

                    savedItems += this.myList.getItemAtPosition(i);

                }

            }

        }

        return savedItems;

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
            accalender.setText(selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear);
        }
    };

    @Override
    public void onBackPressed() {
        Intent i=new Intent(getApplicationContext(),AcService.class);
        getApplicationContext().getSharedPreferences(ACPREFERENCES, 0).edit().clear().apply();
        startActivity(i);

    }

    public void showSnackbar(View view, String message, int duration) {
        Snackbar.make(view, message, duration).show();
    }

}