package com.mansopresk.mansopresk01.acservice.electricalactivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mansopresk.mansopresk01.acservice.MainActivity;
import com.mansopresk.mansopresk01.acservice.R;

public class ElectricalWorks extends AppCompatActivity {
    TextView textView;
    Button electricalimmediate, electricalschedule;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electrical_works);
        electricalimmediate = findViewById(R.id.electricalimmediate);
        electricalschedule = findViewById(R.id.electricalschedule);
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


        electricalimmediate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ElectricalWorks.this,ElectricService.class);
                String selectricalimmediate=electricalimmediate.getText().toString();
                editor = getSharedPreferences("electrical", MODE_PRIVATE).edit();
                editor.putString("electricalimmediate", selectricalimmediate);
                editor.commit();
                startActivity(it);


            }
        });

        electricalschedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ElectricalWorks.this,ElectricService.class);
                String selectricalschedule=electricalschedule.getText().toString();
                editor = getSharedPreferences("electrical", MODE_PRIVATE).edit();
                editor.putString("electricalschedule", selectricalschedule);
                editor.commit();
                startActivity(it);

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i=new Intent(getApplicationContext(),MainActivity.class);
        //getApplicationContext().getSharedPreferences(ELECTRICALMyPREFERENCES, 0).edit().clear().apply();
        startActivity(i);

    }
}
