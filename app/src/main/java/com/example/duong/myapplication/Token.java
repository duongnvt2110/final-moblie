package com.example.duong.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;

import android.content.Context;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class Token extends AppCompatActivity{

    protected String simpleFileName = "token.txt";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            saveToken("test");
            String token = getToken();
            Toast.makeText(getBaseContext(),token,Toast.LENGTH_SHORT).show();

        }

    public void saveToken(String token) {
        String data = token;
        try {
            // Mở một luồng ghi file.
            FileOutputStream out = this.openFileOutput(simpleFileName,Context.MODE_PRIVATE);
            // Ghi dữ liệu.
            out.write(data.getBytes());
            out.close();
//            Toast.makeText(getBaseContext(),"File saved!",Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
                e.printStackTrace();
//            Toast.makeText(this,"Error:"+ e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    public String getToken() {

        final StringBuilder sb= new StringBuilder();

        try {
            // Mở một luồng đọc file.
            FileInputStream in = this.openFileInput(simpleFileName);

            BufferedReader br= new BufferedReader(new InputStreamReader(in));

            String s= null;
            while((s= br.readLine())!= null)  {
                sb.append(s).append("\n");
            }
        } catch (Exception e) {
                e.printStackTrace();
//            Toast.makeText(this,"Error:"+ e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        return sb.toString();
    }
}
