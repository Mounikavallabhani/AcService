package com.mansopresk.mansopresk01.acservice.caractivity;

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

public class CarBikeRepair extends AppCompatActivity {
    TextView textView;
    Button carimmediate, carschedule;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_bike_repair);

        carimmediate = findViewById(R.id.carimmediate);
        carschedule = findViewById(R.id.carschedule);
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


        carimmediate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(CarBikeRepair.this,CarServices.class);
                String scarimmediate=carimmediate.getText().toString();
                editor = getSharedPreferences("carrepair", MODE_PRIVATE).edit();
                editor.putString("carimmediate", scarimmediate);
                editor.commit();
                startActivity(it);


            }
        });

        carschedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(CarBikeRepair.this,CarServices.class);
                String scarschedule=carschedule.getText().toString();
                editor = getSharedPreferences("carrepair", MODE_PRIVATE).edit();
                editor.putString("carschedule", scarschedule);
                editor.commit();
                startActivity(it);

            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent i=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);

    }
}
