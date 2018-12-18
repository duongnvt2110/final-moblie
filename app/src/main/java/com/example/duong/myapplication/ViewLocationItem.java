package com.example.duong.myapplication;


import android.support.v7.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.duong.myapplication.utils.QueryUtils;

import java.util.ArrayList;
import java.util.List;

public class ViewLocationItem  extends AppCompatActivity {
    CustomAdapter customAdaper = null;

    @Override
    protected void onStart() {
        super.onStart();
        this.updateLocation();
    }



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_list);

        final ListView locationList = (ListView) findViewById(R.id.lv_location);
        final ImageView imgLocation = (ImageView) findViewById(R.id.image);
        ArrayList<LocationList> arrContact = new ArrayList<>();

        int id = getResources().getIdentifier("coffee", "drawable", getPackageName());

        customAdaper = new CustomAdapter(ViewLocationItem.this, R.layout.location_view, arrContact);
        locationList.setAdapter(customAdaper);


//        Event Click
        // Set an item click listener for ListView
        locationList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(ViewLocationItem.this, "Button 1 clicked", Toast.LENGTH_SHORT).show();

                LocationList itemChosen = (LocationList) parent.getItemAtPosition(position);

                Intent Intent = new Intent(ViewLocationItem.this, ViewDetailItem.class);
                view.setSelected(true);
                Intent.putExtra("hour","Monday 7 a.m-10 p.m");
                Intent.putExtra("name","Local Coffee");
                Intent.putExtra("id",itemChosen.getId());
                startActivity(Intent);
            }
        });
    }
    private void updateLocation()
    {
        FecthLocationTask task = new FecthLocationTask();
        task.execute();
    }


    public class FecthLocationTask extends AsyncTask<String, Void, List<LocationList>>
    {


        @Override
        protected List<LocationList> doInBackground(String... params) {
            return QueryUtils.fetchLocationData();
        }

        @Override
        protected void onPostExecute(List<LocationList> data) {
            if(data == null || data.isEmpty())
            {
                return;
            }
            else
            {
                customAdaper.addAll(data);

            }

        }
    }
}
