package com.example.duong.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Button;
import android.widget.AdapterView;
import java.util.ArrayList;
import java.util.List;
import com.example.duong.myapplication.LocationList;

public class ViewLocationItem  extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_list);

        final ListView locationList = (ListView) findViewById(R.id.lv_location);
        final ImageView imgLocation = (ImageView) findViewById(R.id.image);
        ArrayList<LocationList> arrContact = new ArrayList<>();

        int id = getResources().getIdentifier("coffee", "drawable", getPackageName());

        LocationList location3 = new LocationList(R.drawable.coffee, "Local Coffee", "Phường Linh Trung - Thủ Đức HCM", 2);
        LocationList location4 = new LocationList(R.drawable.coffee, "Local Coffee", "Phường Linh Trung - Thủ Đức HCM", 2);
        LocationList location5 = new LocationList(R.drawable.coffee, "Local Coffee", "Phường Linh Trung - Thủ Đức HCM", 2);
        LocationList location6 = new LocationList(R.drawable.coffee, "Local Coffee", "Phường Linh Trung - Thủ Đức HCM", 2);
        LocationList location7 = new LocationList(R.drawable.coffee, "Local Coffee", "Phường Linh Trung - Thủ Đức HCM", 2);

        arrContact.add(location3);
        arrContact.add(location4);
        arrContact.add(location5);
        arrContact.add(location6);
        arrContact.add(location7);

        CustomAdapter customAdaper = new CustomAdapter(this, R.layout.location_view, arrContact);
        locationList.setAdapter(customAdaper);


//        Event Click
        // Set an item click listener for ListView
        locationList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemChosen = (String) parent.getItemAtPosition(position);
                Intent intent = new Intent(ViewLocationItem.this, ViewDetailItem.class);
                intent.putExtra("groceryItem", itemChosen);
                startActivity(intent);
            }
        });
    }
}
