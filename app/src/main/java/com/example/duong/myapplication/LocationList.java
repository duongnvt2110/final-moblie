package com.example.duong.myapplication;

import java.util.ArrayList;

public class LocationList {


     private String id;
     private int image;
     private String name;
     private String address;
     private float rating;
     private int distance;
     private String openingTime;
     private ArrayList<ReviewList> reviews;

    public ArrayList<ReviewList> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<ReviewList> reviews) {
        this.reviews = reviews;
    }

    public LocationList(String id, int image, String name, String address, float rating, int distance, String openingTime, ArrayList<ReviewList> reviews) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.address = address;
        this.rating = rating;
        this.distance = distance;

        this.openingTime = openingTime;
        this.reviews = reviews;
    }

    public String getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(String openingTime) {
        this.openingTime = openingTime;
    }

    public LocationList(String id, int image, String name, String address, float rating, int distance, String openingTime) {

        this.id = id;
        this.image = image;
        this.name = name;
        this.address = address;
        this.rating = rating;
        this.distance = distance;
        this.openingTime = openingTime;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocationList(String id, String name, String address, float rating) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.rating = rating;
    }

    public LocationList(String id, int image, String name, String address, float rating, int distance) {

        this.id = id;
        this.image = image;
        this.name = name;
        this.address = address;
        this.rating = rating;
        this.distance = distance;
    }

    public LocationList(int image,String name, String address, int rating, int distance) {
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

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }
    }
