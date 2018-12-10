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
import com.example.duong.myapplication.ReviewList;

public class CustomAdapterReview extends ArrayAdapter<ReviewList> {

    private Context context;
    private int resource;
    private List<ReviewList> arrLocationList ;

    public CustomAdapterReview(Context context, int resource, ArrayList<ReviewList> arrContact) {
        super(context, resource, arrContact);
        this.context = context;
        this.resource = resource;
        this.arrLocationList = arrLocationList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        ReviewList reviewList = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.content_review, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.nameCustomer = (TextView) convertView.findViewById(R.id.nameCustomer);
            viewHolder.message = (TextView) convertView.findViewById(R.id.content);
            viewHolder.rating = (RatingBar) convertView.findViewById(R.id.rating);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.nameCustomer.setText(reviewList.getName());
        viewHolder.message.setText(reviewList.getMessage());
        viewHolder.rating.setTag(position);
        viewHolder.rating.setRating(reviewList.getRating());
        return convertView;
    }

    public class ViewHolder {
        TextView nameCustomer,message;
        RatingBar rating;
        ImageView image;
    }
}