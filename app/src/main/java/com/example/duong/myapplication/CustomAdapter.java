package com.example.duong.myapplication;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.example.duong.myapplication.R;
import com.example.duong.myapplication.LocationList;

public class CustomAdapter extends ArrayAdapter<LocationList> {

    private Context context;
    private int resource;
    private List<LocationList> arrLocationList ;

    public CustomAdapter(Context context, int resource, ArrayList<LocationList> arrContact) {
        super(context, resource, arrContact);
        this.context = context;
        this.resource = resource;
        this.arrLocationList = arrLocationList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        LocationList locationList = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.location_view, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.image = (ImageView) convertView.findViewById(R.id.image);
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.address = (TextView) convertView.findViewById(R.id.address);
            viewHolder.rating = (RatingBar) convertView.findViewById(R.id.rating);
            viewHolder.distance = (TextView) convertView.findViewById(R.id.txt_distance);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.image.setImageResource(locationList.getImage());
        viewHolder.name.setText(locationList.getName());
        viewHolder.address.setText(locationList.getAddress());
        viewHolder.rating.setTag(position);
        viewHolder.rating.setRating(locationList.getRating());
        viewHolder.name.setText(locationList.getName());
        return convertView;
    }

    public class ViewHolder {
        TextView name, address,distance;
        RatingBar rating;
        ImageView image;
    }
}