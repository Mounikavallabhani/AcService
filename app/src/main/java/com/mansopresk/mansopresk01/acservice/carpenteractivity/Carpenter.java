package com.mansopresk.mansopresk01.acservice.carpenteractivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mansopresk.mansopresk01.acservice.MainActivity;
import com.mansopresk.mansopresk01.acservice.R;
import com.mansopresk.mansopresk01.acservice.acactivity.AcService;

public class Carpenter extends AppCompatActivity {

    TextView textView;
    Button carpenterimmediate, carpenterschedule;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carpenter);

        carpenterimmediate = findViewById(R.id.carpenterimmediate);
        carpenterschedule = findViewById(R.id.carpenterschedule);
        Toolbar mToolbar = (Toolbar)findViewById(R.id.toolbarcarp);
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



        carpenterimmediate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Carpenter.this,CarpenterService.class);
                String scarpenterimmediate=carpenterimmediate.getText().toString();
                editor = getSharedPreferences("carpenter", MODE_PRIVATE).edit();
                editor.putString("carpenterimmediate", scarpenterimmediate);
                //Toast.makeText(Carpenter.this, "Immediate clicked", Toast.LENGTH_SHORT).show();
                editor.commit();
                //it.putExtra("ITEM_ID", (Parcelable) carpenterimmediate);
                startActivity(it);


            }
        });

        carpenterschedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Carpenter.this,CarpenterService.class);
                String scarpenterschedule=carpenterschedule.getText().toString();
                editor = getSharedPreferences("carpenter", MODE_PRIVATE).edit();
                editor.putString("carpenterschedule", scarpenterschedule);
               // Toast.makeText(Carpenter.this, "Schedule clicked", Toast.LENGTH_SHORT).show();
                editor.commit();
                //it.putExtra("ITEM_ID1", (Parcelable) carpenterschedule);
                startActivity(it);

            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent i=new Intent(getApplicationContext(),MainActivity.class);
        //getApplicationContext().getSharedPreferences(ACPREFERENCES, 0).edit().clear().apply();
        startActivity(i);

    }
}
