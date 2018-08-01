package com.mansopresk.mansopresk01.acservice.fragments;


import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


import com.mansopresk.mansopresk01.acservice.R;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class AppsFragment extends Fragment {

    View view;
    ArrayList<String> listCountry;
    RadioGroup appradiogroup;
    RadioButton appradiobutton;
    int selectedId;
    SharedPreferences sharedPreferences;
    String appoutput;
    Intent emailIntent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_apps, container, false);



        appradiogroup =  (RadioGroup)view.findViewById(R.id.appradiogroup);
        appradiobutton = (RadioButton)view.findViewById(selectedId);
        sharedPreferences = getActivity().getSharedPreferences("appdetails",MODE_PRIVATE);
        appradiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){
//                selectedId = mehneedgrp .getCheckedRadioButtonId();
//                mehneedbtn = (RadioButton) findViewById(selectedId);
                final View radioButton = appradiogroup.findViewById(checkedId);
                 selectedId = appradiogroup.indexOfChild(radioButton);
                //mehneedbtn = (RadioButton) findViewById(selectedId);

                radioButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int selectdataId = appradiogroup.getCheckedRadioButtonId();
                        appradiobutton = view.findViewById(selectdataId);
                        //Intent i=new Intent(MehandiHireActivity.this,MehendiCostsActivity.class);
                        appoutput = appradiobutton.getText().toString();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("appoutput", appoutput);
                        editor.apply();
                        SharedPreferences appdata = getActivity().getSharedPreferences("appdetails", MODE_PRIVATE);
                        String getappdetails = appdata.getString("appoutput", null);
                        String mail = "lvssrinivas@gmail.com";
                        emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", mail, null));
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Requirement of carpenter Service");
                        emailIntent.putExtra(Intent.EXTRA_TEXT, "Required carpenter Services:\n" + getappdetails);
                        try {
                            getActivity().getSharedPreferences("appdetails", 0).edit().clear().apply();
                            startActivity(emailIntent);

                        } catch (android.content.ActivityNotFoundException ex) {
                            Toast.makeText(getActivity(), "Mail account not configured", Toast.LENGTH_LONG).show();
                        }
                        emailIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    }

                });


            }
        });

        return view;
    }

}
