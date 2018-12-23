package com.example.duong.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duong.myapplication.utils.PostUtils;

import java.io.FileOutputStream;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        final Button btnlg = (Button) findViewById(R.id.btn_lg);
        btnlg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText ed_login = (EditText) findViewById(R.id.lg_name);
                EditText ed_password = (EditText) findViewById(R.id.lg_password);

                String username = ed_login.getText().toString();
                String password = ed_password.getText().toString();

                login(username, password);

            }
        });
    }

    private void login(String username, String password)
    {
        LoginTask task = new LoginTask();
        task.execute(username, password);
    }




    public class LoginTask extends AsyncTask<String, Void, String>
    {


        @Override
        protected String doInBackground(String... params) {
            return PostUtils.login(params[0], params[1]);
        }

        @Override
        protected void onPostExecute(String token) {
            if(token.equals("0"))
            {
                Toast.makeText(Login.this, "Wrong data", Toast.LENGTH_LONG).show();
                return;
            }
            else
            {
                TokenTask tokenTask = new TokenTask();
                tokenTask.execute(token);
                startActivity(new Intent(Login.this, ViewLocationItem.class));

            }

        }
    }

    public class TokenTask extends AsyncTask<String, Void, Integer>
    {


        @Override
        protected Integer doInBackground(String... params) {
            return saveToken(params[0]);
        }

        @Override
        protected void onPostExecute(Integer status) {


        }
    }

    protected String simpleFileName = "token.txt";

    public int saveToken(String token) {
        String data = token;
        try {
            // Mở một luồng ghi file.
            FileOutputStream out = this.openFileOutput(simpleFileName, Context.MODE_PRIVATE);
            // Ghi dữ liệu.
            out.write(data.getBytes());
            out.close();
            return 1;
//            Toast.makeText(getBaseContext(),"File saved!",Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
//            Toast.makeText(this,"Error:"+ e.getMessage(),Toast.LENGTH_SHORT).show();
            return 0;
        }
    }



    }