package com.mansopresk.mansopresk01.acservice.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mansopresk.mansopresk01.acservice.acactivity.AcService;
import com.mansopresk.mansopresk01.acservice.caractivity.CarBikeRepair;
import com.mansopresk.mansopresk01.acservice.carpenteractivity.Carpenter;
import com.mansopresk.mansopresk01.acservice.cleaningactivity.Cleaning;
import com.mansopresk.mansopresk01.acservice.electricalactivity.ElectricalWorks;
import com.mansopresk.mansopresk01.acservice.startactivity.LoginActivity;
import com.mansopresk.mansopresk01.acservice.mehandiactivity.MehendiNeedActivity;
import com.mansopresk.mansopresk01.acservice.packersactivity.PackerMoversService;
import com.mansopresk.mansopresk01.acservice.plumbingactivity.Plumbing;
import com.mansopresk.mansopresk01.acservice.R;
import com.mansopresk.mansopresk01.acservice.SoftwareActivity;

import de.hdodenhof.circleimageview.CircleImageView;


public class HomeFragment extends Fragment
{
    public HomeFragment() {
        // Required empty public constructor
    }
    SharedPreferences sharedPreferences;
    CardView card_view,card_view1,card_view2,card_view3,card_view4,card_view5,card_view6,card_view7,card_viewlast;
ImageView home_software;
    CircleImageView home_ac_repair, home_carpenter, home_car_repair, home_cleaning, home_electrical, home_mehendi, home_plumbing, home_packers_movers ;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_home, container, false);
//
//        home_ac_repair = view.findViewById(R.id.home_ac);
//        home_carpenter = view.findViewById(R.id.home_carpenter);
//        home_car_repair = view.findViewById(R.id.car_repair);
//        home_cleaning = view.findViewById(R.id.cleaning);
//        home_electrical = view.findViewById(R.id.electrical);
//        home_mehendi = view.findViewById(R.id.mehendi);
//        home_plumbing = view.findViewById(R.id.plumbing);
//        home_packers_movers = view.findViewById(R.id.packers);
//        home_software = view.findViewById(R.id.sd);

                card_view =view.findViewById(R.id.card_view);

                card_view1=view.findViewById(R.id.card_view1);

                card_view2=view.findViewById(R.id.card_view2);

                card_view3=view.findViewById(R.id.card_view3);
                card_view4=view.findViewById(R.id.card_view4);
                card_view5=view.findViewById(R.id.card_view5);
                card_view6=view.findViewById(R.id.card_view6);
                card_view7=view.findViewById(R.id.card_view7);
                card_viewlast=view.findViewById(R.id.card_viewlast);





        sharedPreferences =this.getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String uname = sharedPreferences.getString("Name", null);


        if(sharedPreferences!=null){
            if(uname!=null||uname==""){

                Intent it = new Intent(getActivity(), LoginActivity.class);
                startActivity(it);

            }
        }



        card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent i = new Intent(getActivity(), RegistrationActivity.class);
//                startActivity(i);
//                ((Activity) getActivity()).overridePendingTransition(0,0);
                Intent myIntent = new Intent(getActivity(), AcService.class);
                getActivity().startActivity(myIntent);
            }
        });

        card_view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent myIntent = new Intent(getActivity(), Carpenter.class);
                getActivity().startActivity(myIntent);

            }
        });

        card_view2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), CarBikeRepair.class);
                getActivity().startActivity(myIntent);

            }
        });

        card_view3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), Cleaning.class);
                getActivity().startActivity(myIntent);
            }
        });

        card_view4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), ElectricalWorks.class);
                getActivity().startActivity(myIntent);
            }
        });

        card_view5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), MehendiNeedActivity.class);
                getActivity().startActivity(myIntent);
            }
        });
        card_view6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), Plumbing.class);
                getActivity().startActivity(myIntent);
            }
        });
        card_view7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), PackerMoversService.class);
                getActivity().startActivity(myIntent);
            }
        });
        card_viewlast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), SoftwareActivity.class);
                getActivity().startActivity(myIntent);

            }
        });




        return view;
    }

}
