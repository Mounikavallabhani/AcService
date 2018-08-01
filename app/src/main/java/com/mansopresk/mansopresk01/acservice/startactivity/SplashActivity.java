package com.mansopresk.mansopresk01.acservice.startactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.mansopresk.mansopresk01.acservice.R;


public class SplashActivity extends Activity {
    private static int SPLASH_TIME_OUT = 3000;
    LinearLayout ll1 ;

    Animation uptodown;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ll1=findViewById(R.id.ll);


        uptodown = AnimationUtils.loadAnimation(this,R.anim.uptodown);


        ll1.setAnimation(uptodown);


        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

}