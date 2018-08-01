package com.mansopresk.mansopresk01.acservice;

import android.app.Activity;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioButton;

import com.mansopresk.mansopresk01.acservice.fragments.AppsFragment;
import com.mansopresk.mansopresk01.acservice.fragments.WebFragment;


public class SoftwareActivity extends Activity {

    Toolbar toolbar;
    RadioButton rb1, rb2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_software);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);

        rb1 = findViewById(R.id.radioButtonWeb);
        rb2 = findViewById(R.id.radioButtonApps);

        rb1.setChecked(true);
        loadFragment(new WebFragment());

        rb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new WebFragment());
            }
        });

        rb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new AppsFragment());
            }
        });

    }
    private void loadFragment(Fragment fragment) {
// create a FragmentManager
        FragmentManager fm = getFragmentManager();
// create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
// replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.frag_company, fragment);
        fragmentTransaction.commit(); // save the changes
    }
}
