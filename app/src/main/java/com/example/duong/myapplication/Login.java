package com.example.duong.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duong.myapplication.utils.PostUtils;

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


    public class LoginTask extends AsyncTask<String, Void, Integer>
    {


        @Override
        protected Integer doInBackground(String... params) {
            return PostUtils.login(params[0], params[1]);
        }

        @Override
        protected void onPostExecute(Integer status) {
            if(status != 1)
            {
                Toast.makeText(Login.this, "Wrong data", Toast.LENGTH_LONG).show();
                return;
            }
            else
            {
                startActivity(new Intent(Login.this, MainActivity.class));

            }

        }
    }


}