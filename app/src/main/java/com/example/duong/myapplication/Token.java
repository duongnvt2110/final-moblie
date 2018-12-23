package com.example.duong.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;

import android.content.Context;
import android.content.ContextWrapper;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Token extends AppCompatActivity{

    protected String simpleFileName = "token.txt";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            deleteToken(simpleFileName);
//            saveToken("123");
//            String token = getToken();
//            Toast.makeText(getBaseContext(),token,Toast.LENGTH_SHORT).show();

        }

    public void deleteToken(String fileName){

        try{
            File dir = getFilesDir();
            File file = new File(dir, fileName);
            boolean deleted = file.delete();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void saveToken(String token) {
        String data = token;
        try {
            // Mở một luồng ghi file.
            FileOutputStream out= openFileOutput(simpleFileName,0);
            OutputStreamWriter writer= new OutputStreamWriter(out);
            writer.write(data);
            writer.close();
//            FileOutputStream out = this.openFileOutput(simpleFileName,Context.MODE_PRIVATE);
//            // Ghi dữ liệu.
//            out.write(data.getBytes());
//            out.close();
//            Toast.makeText(getBaseContext(),"File saved!",Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
                e.printStackTrace();
//            Toast.makeText(this,"Error:"+ e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    public String getToken() {

        StringBuilder builder=new StringBuilder();

        try {

            FileInputStream in= openFileInput(simpleFileName);
            BufferedReader reader=new
                    BufferedReader(new InputStreamReader(in));
            String data="";

            while((data=reader.readLine())!=null)
            {
                builder.append(data);
                builder.append("\n");
            }
            in.close();

//            // Mở một luồng đọc file.
//            FileInputStream in = this.openFileInput(simpleFileName);
//
//            BufferedReader br= new BufferedReader(new InputStreamReader(in));
//
//            String s= null;
//            while((s= br.readLine())!= null)  {
//                sb.append(s).append("\n");
//            }
        } catch (Exception e) {
                e.printStackTrace();
//            Toast.makeText(this,"Error:"+ e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        return builder.toString();
    }
}
