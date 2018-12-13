package com.example.duong.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class LocationList {
        private int image;
        private String name;
        private String address;
        private float rating;
        private String distance;


    public LocationList(int image,String name, String address, int rating, String distance) {
            this.image = image;
            this.name = name;
            this.address = address;
            this.rating = rating;
            this.distance= distance;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public float getRating() {
            return rating;
        }

        public void setRating(int rating) {
            this.rating = rating;
        }


        public int getImage() {
            return image;
        }

        public void setImage(int image) {
            this.image = image;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }
    }
