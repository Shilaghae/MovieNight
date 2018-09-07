package com.movienight.data;

import com.google.gson.annotations.SerializedName;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author anna
 */

public class Movie implements Parcelable {

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public void setMovieRating(String movieRating) {
        this.movieRating = movieRating;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public void setMoviePosterImagePath(String moviePosterImagePath) {
        this.moviePosterImagePath = moviePosterImagePath;
    }

    public void setMoviePlot(String moviePlot) {
        this.moviePlot = moviePlot;
    }

    public void setMovieRelease(String movieRelease) {
        this.movieRelease = movieRelease;
    }

    @SerializedName("title")
    private String movieTitle;

    @SerializedName("vote_average")
    private String movieRating;

    @SerializedName("id")
    private int movieId;

    @SerializedName("poster_path")
    private String moviePosterImagePath;

    @SerializedName("overview")
    private String moviePlot;

    @SerializedName("release_date")
    private String movieRelease;

    protected Movie(Parcel in) {
        movieTitle = in.readString();
        movieRating = in.readString();
        movieId = in.readInt();
        moviePosterImagePath = in.readString();
        moviePlot = in.readString();
        movieRelease = in.readString();
    }

    public Movie() {

    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getMovieTitle() {
        return movieTitle;
    }

    public String getMovieRating() {
        return movieRating;
    }

    public String getMoviePosterImagePath() {
        return moviePosterImagePath;
    }

    public String getMoviePlot() {
        return moviePlot;
    }

    public String getMovieRelease() {
        return movieRelease;
    }

    public int getMovieId() {
        return movieId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(movieTitle);
        parcel.writeString(movieRating);
        parcel.writeInt(movieId);
        parcel.writeString(moviePosterImagePath);
        parcel.writeString(moviePlot);
        parcel.writeString(movieRelease);
    }
}
