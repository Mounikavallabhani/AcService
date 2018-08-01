package com.mansopresk.mansopresk01.acservice.packersactivity;

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

public class PackerMoversService extends AppCompatActivity {
    TextView textView;
    Button packersimmediate, pekersschedule;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packer_movers_service);
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


        packersimmediate = findViewById(R.id.packersimmediate);
        pekersschedule = findViewById(R.id.pekersschedule);


        packersimmediate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(),PackersMovers.class);
                String spackersimmediate=packersimmediate.getText().toString();
                editor = getSharedPreferences("packersandmovers", MODE_PRIVATE).edit();
                editor.putString("packersimmediate", spackersimmediate);
                editor.commit();
                startActivity(it);


            }
        });

        pekersschedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(),PackersMovers.class);
                String spekersschedule=pekersschedule.getText().toString();
                editor = getSharedPreferences("plumbing", MODE_PRIVATE).edit();
                editor.putString("pekersschedule", spekersschedule);
                editor.commit();
                startActivity(it);

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i=new Intent(getApplicationContext(),MainActivity.class);
//        getApplicationContext().getSharedPreferences(PACKERSMyPREFERENCES, 0).edit().clear().apply();
        startActivity(i);

    }
}
