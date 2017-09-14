package com.codepath.codepath1.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {

    public String overview;
    public String title;
    public String posterPath;
    public String backDropPath;
    public String popularity;
    public String voteAverage;

    public Movie(String title,
        String overview,
        String posterPath,
        String backDropPath,
        String popularity,
        String voteAverage) {
        this.overview = overview;
        this.title = title;
        this.posterPath = posterPath;
        this.backDropPath = backDropPath;
        this.popularity = popularity;
        this.voteAverage = voteAverage;
    }

    public Movie(Parcel in){
        String[] data = new String[6];

        in.readStringArray(data);
        // the order needs to be the same as in writeToParcel() method
        this.title = data[0];
        this.overview = data[1];
        this.posterPath = data[2];
        this.backDropPath = data[3];
        this.popularity = data[4];
        this.voteAverage = data[5];
    }
    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {
            this.title,
            this.overview,
            this.posterPath,
            this.backDropPath,
            this.popularity,
            this.voteAverage});
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
