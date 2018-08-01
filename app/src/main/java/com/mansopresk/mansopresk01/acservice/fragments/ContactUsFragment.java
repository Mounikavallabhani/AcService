package com.mansopresk.mansopresk01.acservice.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mansopresk.mansopresk01.acservice.MainActivity;
import com.mansopresk.mansopresk01.acservice.R;


public class ContactUsFragment extends Fragment
{

    TextView first_no, second_no,mail_tv;

  //  private GoogleMap mMap;



    public ContactUsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact_us, container, false);

        first_no = view.findViewById(R.id.first_no);
        second_no = view.findViewById(R.id.second_no);
        mail_tv = view.findViewById(R.id.mail_tv);
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


//        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
//                .findFragmentById(R.id.contact_map);
//        mapFragment.getMapAsync(this);

        first_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:"+"91 799 355 2864"));
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });

        second_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:"+"91 916 028 5374"));
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });

        mail_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setType("text/html");
                intent.putExtra(Intent.EXTRA_EMAIL, "info@mansopresk.com");
//                intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
//                intent.putExtra(Intent.EXTRA_TEXT, "I'm email body.");

                startActivity(Intent.createChooser(intent, "Send Email"));
            }
        });

        return view;
    }

//    @Override
//    public void onMapReady(GoogleMap googleMap)
//    {
//        mMap = googleMap;
//
//        // Add a marker in Sydney and move the camera
//        LatLng manso = new LatLng(17.4573406, 78.35111059999997);
////        String url = "http://maps.google.com/maps/api/staticmap?center=" + manso +"&zoom=15&size=200x200&sensor=false";
//        mMap.addMarker(new MarkerOptions().position(manso).title("Mansopresk Pvt Ltd"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(manso));
//        mMap.animateCamera(CameraUpdateFactory.zoomTo(11),2000,null);
//        mMap.getUiSettings().setZoomControlsEnabled(true);
//    }
}
