package com.mansopresk.mansopresk01.acservice.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.mansopresk.mansopresk01.acservice.R;

import static android.content.Context.MODE_PRIVATE;


public class WebFragment extends Fragment {
    RadioGroup webrediogroup;
    int selectedId;
    SharedPreferences sharedPreferences;
    RadioButton webradiobutton;
    String weboutput;
    Intent emailIntent;

    View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_web, container, false);


        webrediogroup =  (RadioGroup)view.findViewById(R.id.webrediogroup);
        webradiobutton = (RadioButton)view.findViewById(selectedId);
        sharedPreferences = getActivity().getSharedPreferences("webdetails",MODE_PRIVATE);
        webrediogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){
//                selectedId = mehneedgrp .getCheckedRadioButtonId();
//                mehneedbtn = (RadioButton) findViewById(selectedId);
                final View radioButton = webrediogroup.findViewById(checkedId);
                selectedId = webrediogroup.indexOfChild(radioButton);
                //mehneedbtn = (RadioButton) findViewById(selectedId);

                radioButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int selectdataId = webrediogroup.getCheckedRadioButtonId();
                        webradiobutton = view.findViewById(selectdataId);
                        //Intent i=new Intent(MehandiHireActivity.this,MehendiCostsActivity.class);
                        weboutput = webradiobutton.getText().toString();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("weboutput", weboutput);
                        editor.apply();
                        SharedPreferences webdata = getActivity().getSharedPreferences("webdetails", MODE_PRIVATE);
                        String getappdetails = webdata.getString("weboutput", null);
                        String mail = "lvssrinivas@gmail.com";
                        emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", mail, null));
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Requirement of carpenter Service");
                        emailIntent.putExtra(Intent.EXTRA_TEXT, "Required carpenter Services:\n" + getappdetails);
                        try {
                            getActivity().getSharedPreferences("webdetails", 0).edit().clear().apply();
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
