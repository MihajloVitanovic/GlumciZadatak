package com.mvit.mihajlo.glumcizadatak.db.model;

import android.graphics.Movie;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by androiddevelopment on 13.6.17..
 */
@DatabaseTable(tableName = Actor.TABLE_NAME_USERS)
public class Actor {
    public static final String TABLE_NAME_USERS = "actors";

    public static final String FIELD_NAME_ID     = "id";
    public static final String FIELD_NAME_NAME   = "name";
    public static final String FIELD_NAME_LASTNAME   = "lastname";
    public static final String FIELD_NAME_SUMMARY   = "summary";
    public static final String FIELD_NAME_RATING   = "rating";
    public static final String FIELD_NAME_YEAR  = "year";
    public static final String FIELD_NAME_MOVIES = "movies";
    public static final String FIELD_NAME_GENRE = "genre";


    @DatabaseField(columnName = FIELD_NAME_ID, generatedId = true)
    private int mId;

    @DatabaseField(columnName = FIELD_NAME_NAME)
    private String mName;

    @DatabaseField(columnName = FIELD_NAME_LASTNAME)
    private String lastname;

    @DatabaseField(columnName = FIELD_NAME_SUMMARY)
    private String summary;

    @DatabaseField(columnName = FIELD_NAME_RATING)
    private float rating;

    @DatabaseField(columnName = FIELD_NAME_YEAR)
    private String year;

    @DatabaseField(columnName = FIELD_NAME_MOVIES)
    private float movies;

    @DatabaseField(columnName = FIELD_NAME_GENRE)
    private String genre;

    public Actor(){}

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public float getMovies() {
        return movies;
    }

    public void setMovies(float movies) {
        this.movies = movies;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
