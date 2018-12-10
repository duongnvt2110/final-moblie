package com.example.duong.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Button;
import java.util.ArrayList;
import java.util.List;
import com.example.duong.myapplication.LocationList;

public class ViewDetailItem  extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_location);

//               demo add list review
            ListView lvReviewList= (ListView) findViewById(R.id.dt_location);
            ArrayList<ReviewList> arrContact = new ArrayList<>();



            ReviewList review1 = new ReviewList("ToBi","My favorite drink is coffe and this place is very good for mixed coffee",5);
            ReviewList review2 = new ReviewList("Hoàng","The same message in above",5);

            arrContact.add(review1);
            arrContact.add(review2);

            CustomAdapterReview customAdaper = new CustomAdapterReview(this,R.layout.detail_location,arrContact);
            lvReviewList.setAdapter(customAdaper);

    }
}
