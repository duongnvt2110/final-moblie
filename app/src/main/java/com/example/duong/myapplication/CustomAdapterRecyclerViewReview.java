package com.example.duong.myapplication;

import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;


//Link https://gist.github.com/chRyNaN/fd433c680b85b2ef267448b75ecac0ae

public class CustomAdapterRecyclerViewReview extends
        RecyclerView.Adapter<CustomAdapterRecyclerViewReview.ViewHolder> {
    private List<ReviewList> item;
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nameCustomer;
        public TextView message;
        public RatingBar rating;

        public ViewHolder(View itemView) {

            super(itemView);
            nameCustomer = (TextView) itemView.findViewById(R.id.nameCustomer);
            message = (TextView) itemView.findViewById(R.id.content);
            rating = (RatingBar) itemView.findViewById(R.id.rating);
        }


    }
    private List<ReviewList> reviews ;

    // Pass in the contact array into the constructor
    public CustomAdapterRecyclerViewReview(List<ReviewList> reviews) {
        this.reviews = reviews;
    }
    @Override
    public CustomAdapterRecyclerViewReview.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View reviewView = inflater.inflate(R.layout.content_review, parent, false);
        ViewHolder viewHolder = new ViewHolder(reviewView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomAdapterRecyclerViewReview.ViewHolder viewHolder, int position) {

        ReviewList review = reviews.get(position);
        viewHolder.nameCustomer.setText(review.getName());
        viewHolder.message.setText(review.getMessage());
        viewHolder.rating.setRating(review.getRating());

    }

    // Return the total count of items
    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public void addAll(ArrayList<ReviewList> items){
        if(this.item == null){
            this.item = new ArrayList<>();
        }
        items.addAll(items);
        notifyDataSetChanged();
    }
}
