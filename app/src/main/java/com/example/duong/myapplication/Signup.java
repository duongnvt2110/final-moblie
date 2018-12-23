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

public class Signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        final Button btnsu = (Button) findViewById(R.id.btn_su);
        btnsu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText su_email = (EditText) findViewById(R.id.su_email);
                EditText su_name = (EditText) findViewById(R.id.su_name);
                EditText su_password = (EditText) findViewById(R.id.su_password);

                String email = su_email.getText().toString();
                String name = su_name.getText().toString();
                String password = su_password.getText().toString();
                signUp(email, name, password);
            }
        });
    }


    private void signUp(String email, String name, String password)
    {
        SignupTask task = new SignupTask();
        task.execute(email, name, password);
    }


    public class SignupTask extends AsyncTask<String, Void, String>
    {


        @Override
        protected String doInBackground(String... params) {
            return PostUtils.register(params[0], params[1], params[2]);
        }

        @Override
        protected void onPostExecute(String token) {
            if(token.equals("0"))
            {
                Toast.makeText(Signup.this, "Wrong data", Toast.LENGTH_LONG).show();
                return;
            }
            else
            {
                TokenTask tokenTask = new TokenTask();
                tokenTask.execute(token);
                startActivity(new Intent(Signup.this, MainActivity.class));

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
