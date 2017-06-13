package com.mvit.mihajlo.glumcizadatak.models;

import java.util.Date;

/**
 * Created by androiddevelopment on 13.6.17..
 */

public class Actor {
    private int id;
    private String image;
    private String firstName;
    private String lastName;
    private String summary;
    private float rating;
    private Date year;
    private String movies;

    public Actor() {
    }

    public Actor(int id, String image, String firstName, String lastName, String summary, float rating, Date year, String movies) {
        this.id = id;
        this.image = image;
        this.firstName = firstName;
        this.lastName = lastName;
        this.summary = summary;
        this.rating = rating;
        this.year = year;
        this.movies = movies;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public String getMovies() {
        return movies;
    }

    public void setMovies(String movies) {
        this.movies = movies;
    }

    @Override
    public String toString() {
        return firstName + " " +lastName;
    }
}

