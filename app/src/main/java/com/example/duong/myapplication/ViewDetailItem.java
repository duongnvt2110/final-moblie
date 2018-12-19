package com.example.duong.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.duong.myapplication.utils.QueryUtils;

import java.util.ArrayList;

public class ViewDetailItem  extends AppCompatActivity {

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        final String id = intent.getStringExtra("id");
        this.getDetailLocation(id);
    }

    private void displayData(LocationList location){
        final TextView txtName= (TextView) findViewById(R.id.txt_name);
        final TextView txtAddress = (TextView) findViewById(R.id.txt_address);
        final RatingBar rbRating = (RatingBar) findViewById(R.id.rb_rating);
        final TextView txtHour= (TextView) findViewById(R.id.txt_hour);
        txtName.setText(location.getName());
        txtAddress.setText(location.getAddress());
        rbRating.setRating(location.getRating());
        txtHour.setText(location.getOpeningTime());

    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_location);

//            create Listview and add into ReviewList
            ListView lvReviewList= (ListView) findViewById(R.id.dt_location);
            ArrayList<ReviewList> arrContact = new ArrayList<>();


            ReviewList review1 = new ReviewList("ToBi","My favorite drink is coffe and this place is very good for mixed coffee",5);
            ReviewList review2 = new ReviewList("Hoàng","The same message in above",5);

            arrContact.add(review1);
            arrContact.add(review2);

//            set content custom adapter
            CustomAdapterReview customAdaper = new CustomAdapterReview(this,R.layout.detail_location,arrContact);
            lvReviewList.setAdapter(customAdaper);

//            declacre btn txt and catch event click
            final TextView tvView= (TextView) findViewById(R.id.btn_addreview);

            tvView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    Intent Intent = new Intent(ViewDetailItem.this, AddReview.class);
                    startActivity(Intent);
                }
            });

    }
    private void getDetailLocation(String id)
    {
        FecthDetailLocationTask task = new FecthDetailLocationTask();
        task.execute(id);
    }


    public class FecthDetailLocationTask extends AsyncTask<String, Void, LocationList>
    {


        @Override
        protected LocationList doInBackground(String... params) {
            String id = params[0];
            return QueryUtils.fetchDetailLocationData(id);
        }

        @Override
        protected void onPostExecute(LocationList data) {
            if(data == null)
            {
                return;
            }
            else
            {
                Log.d("Detail", data.toString());
                displayData(data);

            }

        }
    }
}
