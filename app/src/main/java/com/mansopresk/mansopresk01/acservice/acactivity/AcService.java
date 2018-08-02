package com.mansopresk.mansopresk01.acservice.acactivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mansopresk.mansopresk01.acservice.AcCalnder;
import com.mansopresk.mansopresk01.acservice.MainActivity;
import com.mansopresk.mansopresk01.acservice.R;

public class AcService extends AppCompatActivity {

    TextView textView;
    Button acimmediate, acschedule;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acservice);
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



        acimmediate = findViewById(R.id.acimmediate);
        acschedule = findViewById(R.id.acschedule);


        acimmediate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(AcService.this,ImmediateService.class);
                String sacimbutton=acimmediate.getText().toString();
                editor = getSharedPreferences("acservice", MODE_PRIVATE).edit();
                editor.putString("acimmediate", sacimbutton);
                editor.commit();
                startActivity(it);


            }
        });

        acschedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(AcService.this,ImmediateService.class);
                String sacschbutton=acschedule.getText().toString();
                editor = getSharedPreferences("acservice", MODE_PRIVATE).edit();
                editor.putString("acschedule", sacschbutton);
                editor.commit();
                startActivity(it);

            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent i=new Intent(getApplicationContext(),MainActivity.class);
       // getApplicationContext().getSharedPreferences(ACPREFERENCES, 0).edit().clear().apply();
        startActivity(i);

    }
}