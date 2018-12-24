package com.example.duong.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

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

        Glide.with(context).asBitmap().load(locationList.getImage())
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.AUTOMATIC)

                )
                .into(viewHolder.image);
        viewHolder.name.setText(locationList.getName());
        viewHolder.address.setText(locationList.getAddress());
        viewHolder.rating.setTag(position);
        viewHolder.rating.setRating(locationList.getRating());
        viewHolder.name.setText(locationList.getName());
        viewHolder.distance.setText(locationList.getDistance() + "m");
        return convertView;
    }

    public class ViewHolder {
        TextView name, address,distance;
        RatingBar rating;
        ImageView image;
    }
}