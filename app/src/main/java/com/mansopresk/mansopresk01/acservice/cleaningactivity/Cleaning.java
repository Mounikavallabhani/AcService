package com.mansopresk.mansopresk01.acservice.cleaningactivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.mansopresk.mansopresk01.acservice.AcCalnder;
import com.mansopresk.mansopresk01.acservice.MainActivity;
import com.mansopresk.mansopresk01.acservice.R;

public class Cleaning extends AppCompatActivity {
    Button cleaningimmediate, cleaningschedule;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cleaning);


        cleaningimmediate = findViewById(R.id.cleaningimmediate);
        cleaningschedule = findViewById(R.id.cleaningschedule);
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

        cleaningimmediate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(),CleaningService.class);
                String scleaningimmediate=cleaningimmediate.getText().toString();
                editor = getSharedPreferences("cleaning", MODE_PRIVATE).edit();
                editor.putString("cleaningimmediate", scleaningimmediate);
                editor.commit();
                //it.putExtra("ITEM_ID", (Parcelable) carpenterimmediate);
                startActivity(it);


            }
        });

        cleaningschedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(),CleaningService.class);
                String scleaningschedule=cleaningschedule.getText().toString();
                editor = getSharedPreferences("cleaning", MODE_PRIVATE).edit();
                editor.putString("cleaningschedule", scleaningschedule);
                editor.commit();
                //it.putExtra("ITEM_ID1", (Parcelable) carpenterschedule);
                startActivity(it);

            }
        });
    }

    }

