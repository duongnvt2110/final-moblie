package com.example.duong.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.duong.myapplication.utils.PostUtils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class AddReview  extends AppCompatActivity {

    private String locationId;

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        locationId = intent.getStringExtra("locationId");
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_review);
        final Button btnlg = (Button) findViewById(R.id.btn_send);
        btnlg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AddTask addTask = new AddTask();
                addTask.execute();
            }
        });
    }



    private void addReview(String locationId, String token, String rating, String reviewText)
    {
        AddReviewTask task = new AddReviewTask();
        task.execute(locationId, token, rating, reviewText);
    }




    public class AddReviewTask extends AsyncTask<String, Void, Integer>
    {


        @Override
        protected Integer doInBackground(String... params) {
            return PostUtils.addReview(params[0], params[1], params[2], params[3]);
        }

        @Override
        protected void onPostExecute(Integer status) {
            if(status != 1)
            {
                Toast.makeText(AddReview.this, "Thêm đánh giá thất bại", Toast.LENGTH_LONG).show();
                return;
            }
            else
            {
                Toast.makeText(AddReview.this, "Thêm đánh giá thành công!", Toast.LENGTH_LONG).show();
                finish();
            }

        }
    }

    public class AddTask extends AsyncTask<String, Void, String>
    {

        @Override
        protected String doInBackground(String... params) {
            return getToken();
        }

        @Override
        protected void onPostExecute(String token) {
            RatingBar rating = (RatingBar)findViewById(R.id.rv_rating);
            EditText text = (EditText) findViewById(R.id.rv_text);
            addReview(token, locationId,Float.toString(rating.getRating()), text.getText().toString());
        }
    }

    protected String simpleFileName = "token.txt";

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