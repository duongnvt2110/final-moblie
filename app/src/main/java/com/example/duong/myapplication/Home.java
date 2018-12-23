package com.example.duong.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.File;
public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String simpleFileName = "token.txt";
        if(fileExist(simpleFileName)){
            startActivity(new Intent(Home.this, MainActivity.class));
        }else{
            setContentView(R.layout.home);
            final Button btnLogin = (Button) findViewById(R.id.btn_login);
            final Button btnSignup = (Button) findViewById(R.id.btn_signup);
            btnLogin.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    startActivity(new Intent(Home.this, Login.class));
                }
            });
            btnSignup.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    startActivity(new Intent(Home.this, Signup.class));
                }
            });
        }

    }

    public boolean fileExist(String fname){
        File file = getBaseContext().getFileStreamPath(fname);
        return file.exists();
    }
}
