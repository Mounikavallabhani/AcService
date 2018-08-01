package com.mansopresk.mansopresk01.acservice.fragments;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mansopresk.mansopresk01.acservice.startactivity.LoginActivity;
import com.mansopresk.mansopresk01.acservice.MainActivity;
import com.mansopresk.mansopresk01.acservice.R;

import static android.content.Context.MODE_PRIVATE;


public class PersonFragment extends Fragment  {
    public PersonFragment() {
        // Required empty public constructor
    }

    TextView username, mailid, mobileno;
    Button logout;
    SharedPreferences sharedpreferences;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.profile, container, false);
        username = view.findViewById(R.id.username);
        mailid = view.findViewById(R.id.mailid);
        mobileno = view.findViewById(R.id.mobileno);
        logout = view.findViewById(R.id.logout);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        Toolbar mToolbar = (Toolbar) view.findViewById(R.id.toolbarcredits);
//        mToolbar.setTitle("Mansopresk");
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i2 = new Intent(getActivity(),MainActivity.class);
                startActivity(i2);
                //finish();
            }
        });



   view.setOnKeyListener(new View.OnKeyListener() {
    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if(event.getAction()==KeyEvent.ACTION_DOWN) {
            if(keyCode==KeyEvent.KEYCODE_BACK) {


                Intent myIntent = new Intent(getActivity(), MainActivity.class);

                // getActivity().getSharedPreferences("Registration", 0).edit().clear().apply();
                getActivity().startActivity(myIntent);

            }
        }
        return false;
    }
});


        sharedpreferences = this.getActivity().getSharedPreferences("Registration", MODE_PRIVATE);

        String profileuname = sharedpreferences.getString("username", null);
        String profilemobilenum = sharedpreferences.getString("mobile", null);
        String profileemailid = sharedpreferences.getString("email", null);
        username.setText(profileuname);
        mailid.setText(profileemailid);
        mobileno.setText(profilemobilenum);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), LoginActivity.class);

               getActivity().getSharedPreferences("Registration", 0).edit().clear().apply();
                getActivity().getSharedPreferences("logindetails", 0).edit().clear().apply();
                getActivity().startActivity(myIntent);
            }
        });


        return view;

    }

}
