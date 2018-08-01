package com.mansopresk.mansopresk01.acservice.plumbingactivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mansopresk.mansopresk01.acservice.MainActivity;
import com.mansopresk.mansopresk01.acservice.R;

public class Plumbing extends Activity {
    TextView textView;
    Button plumbingimmediate, plumbingschedule;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plumbing);
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


        plumbingimmediate = findViewById(R.id.plumbingimmediate);
        plumbingschedule = findViewById(R.id.plumbingschedule);


        plumbingimmediate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Plumbing.this,PlumbingService.class);
                String splumbingimmediate=plumbingimmediate.getText().toString();
                editor = getSharedPreferences("plumbing", MODE_PRIVATE).edit();
                editor.putString("plumbingimmediate", splumbingimmediate);
                editor.commit();
                startActivity(it);


            }
        });

        plumbingschedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Plumbing.this,PlumbingService.class);
                String splumbingschedule=plumbingschedule.getText().toString();
                editor = getSharedPreferences("plumbing", MODE_PRIVATE).edit();
                editor.putString("plumbingschedule", splumbingschedule);
                editor.commit();
                startActivity(it);

            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent i=new Intent(getApplicationContext(),MainActivity.class);
       // getApplicationContext().getSharedPreferences(PLUMBINGMyPREFERENCES, 0).edit().clear().apply();
        startActivity(i);

    }
}
