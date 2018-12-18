package com.example.duong.myapplication.models;
import  java.util.Date;
public class Location {
    private String id;
    private String name;
    private String address;
    private Number rating;
    private String[] facilities;
    private Long[] coords;
    private OpeningTime[] openingTimes;

    public Location(String id, String name, String address, Number rating) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.rating = rating;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Location(String id, String name, String address, Number rating, String[] facilities, Long[] coords, OpeningTime[] openingTimes, Review[] reviews) {

        this.id = id;
        this.name = name;
        this.address = address;
        this.rating = rating;
        this.facilities = facilities;
        this.coords = coords;
        this.openingTimes = openingTimes;
        this.reviews = reviews;
    }

    private Review[] reviews;

    public Location(String name, String address, Number rating, String[] facilities, Long[] coords, OpeningTime[] openingTimes, Review[] reviews) {
        this.name = name;
        this.address = address;
        this.rating = rating;
        this.facilities = facilities;
        this.coords = coords;
        this.openingTimes = openingTimes;
        this.reviews = reviews;
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

    public Number getRating() {
        return rating;
    }

    public void setRating(Number rating) {
        this.rating = rating;
    }

    public String[] getFacilities() {
        return facilities;
    }

    public void setFacilities(String[] facilities) {
        this.facilities = facilities;
    }

    public Long[] getCoords() {
        return coords;
    }

    public void setCoords(Long[] coords) {
        this.coords = coords;
    }

    public OpeningTime[] getOpeningTimes() {
        return openingTimes;
    }

    public void setOpeningTimes(OpeningTime[] openingTimes) {
        this.openingTimes = openingTimes;
    }

    public Review[] getReviews() {
        return reviews;
    }

    public void setReviews(Review[] reviews) {
        this.reviews = reviews;
    }
}

class OpeningTime{
    private String days;
    private String opening;
    private String closing;
    private Boolean closed;

    public OpeningTime(String days, String opening, String closing, Boolean closed) {
        this.days = days;
        this.opening = opening;
        this.closing = closing;
        this.closed = closed;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getOpening() {
        return opening;
    }

    public void setOpening(String opening) {
        this.opening = opening;
    }

    public String getClosing() {
        return closing;
    }

    public void setClosing(String closing) {
        this.closing = closing;
    }

    public Boolean getClosed() {
        return closed;
    }

    public void setClosed(Boolean closed) {
        this.closed = closed;
    }
}

class  Review{
    private String author;
    private Number rating;
    private String reviewText;
    private Date createdOn;

    public Review(String author, Number rating, String reviewText, Date createdOn) {
        this.author = author;
        this.rating = rating;
        this.reviewText = reviewText;
        this.createdOn = createdOn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Number getRating() {
        return rating;
    }

    public void setRating(Number rating) {
        this.rating = rating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
}