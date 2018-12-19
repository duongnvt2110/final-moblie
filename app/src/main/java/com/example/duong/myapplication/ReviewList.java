package com.example.duong.myapplication;

public class ReviewList {
    private String id;
    private String name;
    private String message;
    private float rating;

    public ReviewList(String id, String name, String message, float rating) {
        this.id = id;
        this.name = name;
        this.message = message;
        this.rating = rating;
    }

    public ReviewList(String name, String message, int rating) {
        this.name = name;
        this.message = message;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String address) {
        this.message = message;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

}
