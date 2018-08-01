package com.mansopresk.mansopresk01.acservice.startactivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mansopresk.mansopresk01.acservice.MainActivity;
import com.mansopresk.mansopresk01.acservice.R;


public class LoginActivity extends Activity {
    EditText textInputEditTextEmail,textInputLayoutPassword;
    Button appCompatButtonLogin;
    TextView textViewLinkRegister,register,textViewLinkforger;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textInputEditTextEmail=findViewById(R.id.textInputEditTextEmail);
        textInputLayoutPassword=findViewById(R.id.textInputLayoutPassword);
        appCompatButtonLogin=findViewById(R.id.appCompatButtonLogin);
        textViewLinkRegister=findViewById(R.id.textViewLinkRegister);
        register=findViewById(R.id.textViewLinkRegister);
        textViewLinkforger=findViewById(R.id.textViewLinkforger);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it =new Intent(LoginActivity.this,RegistrationActivity.class);
                startActivity(it);
            }
        });
        sharedPreferences=getSharedPreferences("logindetails", Context.MODE_PRIVATE);
        String uname=sharedPreferences.getString("email",null);
        if(uname!=null)
        {
            Intent it=new Intent(LoginActivity.this,MainActivity.class);
            startActivity(it);
        }



        appCompatButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               if (textInputEditTextEmail.getText().toString().isEmpty()) {
                    textInputEditTextEmail.requestFocus();
                    textInputEditTextEmail.setError("please provide email id");

                } else if (textInputLayoutPassword.getText().toString().isEmpty()) {
                   textInputLayoutPassword.requestFocus();
                   textInputLayoutPassword.setError("please provide password");

                }
                else {

                    String email = textInputEditTextEmail.getText().toString();
                    String password=textInputLayoutPassword.getText().toString();


                    if (email.contains("@") && email.contains((".com"))) {
                        editor = getSharedPreferences("logindetails", MODE_PRIVATE).edit();

                        editor.putString("password", password);
                        editor.putString("email", email);
                        editor.commit();
                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(i);
                    } else {
                        textInputEditTextEmail.requestFocus();
                        textInputEditTextEmail.setError("please provide email id");
                    }
                }

            }
        });

    }
    public void showSnackbar(View view, String message, int duration) {
        Snackbar.make(view, message, duration).show();
    }

    public  void forget(View v){
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.layout_custom_dialog, null);
        final EditText mail = alertLayout.findViewById(R.id.fmail);
        final Button ok=alertLayout.findViewById(R.id.fok);
        final Button cancel=alertLayout.findViewById(R.id.fcancel);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Forgot Password");
        alert.setView(alertLayout);
        alert.setCancelable(false);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String smail=mail.getText().toString().trim();
                if (smail.contains("@") && smail.contains(".com")) {
                    Intent it = new Intent(LoginActivity.this, LoginActivity.class);
                    startActivity(it);
                }
                else if (mail.getText().toString().trim().isEmpty()) {
                    mail.requestFocus();
                    mail.setError("Enter valid Email");
                }
                else {
                    Toast.makeText(LoginActivity.this, " enter proper mail id ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(LoginActivity.this,LoginActivity.class);
                startActivity(it);
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }

}