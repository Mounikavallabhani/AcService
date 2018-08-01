package com.mansopresk.mansopresk01.acservice;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.mansopresk.mansopresk01.acservice.fragments.AreaCoverageFragment;
import com.mansopresk.mansopresk01.acservice.fragments.ContactUsFragment;
import com.mansopresk.mansopresk01.acservice.fragments.ForCorporateFragment;
import com.mansopresk.mansopresk01.acservice.fragments.HomeFragment;
import com.mansopresk.mansopresk01.acservice.fragments.OrderFragment;
import com.mansopresk.mansopresk01.acservice.fragments.PersonFragment;
import com.mansopresk.mansopresk01.acservice.startactivity.LoginActivity;


public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadFragment(new HomeFragment());


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);



        sharedPreferences=getSharedPreferences("logindetails", Context.MODE_PRIVATE);
        String uname=sharedPreferences.getString("email",null);
        if(sharedPreferences!=null)
        {

        }
        else
        {
            Intent it=new Intent(MainActivity.this,LoginActivity.class);
            startActivity(it);
        }
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment fragment = null;

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = new HomeFragment();
                    break;
//                case R.id.navigation_profile:
//                    fragment = new AboutUsFragment();
//                    break;
                case R.id.navigation_area_coverage:
                    fragment = new OrderFragment();
                    break;
                case R.id.navigation_for_corporate:
                    fragment = new ForCorporateFragment();
                    break;
                case R.id.navigation_contact_us:
                    fragment = new ContactUsFragment();
                    break;
                case R.id.navigation_profile:
                    fragment = new PersonFragment();
                    break;


            }
            return loadFragment(fragment);
        }
    };
    private boolean loadFragment(Fragment fragment)
    {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
        startActivity(intent);
        finish();
        System.exit(0);
    }

}
