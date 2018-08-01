package com.mansopresk.mansopresk01.acservice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    LinearLayout linearLayout1,linearLayout2;
    TextView acwindow,acsplit;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        acwindow=findViewById(R.id.windowac);
        acsplit=findViewById(R.id.splitac);


        linearLayout1=findViewById(R.id.actype1);
        linearLayout2=findViewById(R.id.actype2);



        linearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Main2Activity.this,FinalActivity.class);
                editor = getSharedPreferences("acservicedetails", MODE_PRIVATE).edit();
                String windowtext =acwindow.getText().toString();
                editor.putString("acwindow", windowtext);
                editor.commit();
                startActivity(it);
            }
        });
        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Main2Activity.this,FinalActivity.class);
                editor = getSharedPreferences("acservicedetails", MODE_PRIVATE).edit();
                String splittext =acsplit.getText().toString();
                editor.putString("acsplit", splittext);
                editor.commit();
                startActivity(it);
            }
        });
    }
}
