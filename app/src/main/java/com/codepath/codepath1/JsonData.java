package com.codepath.codepath1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class JsonData {

    public List<Movie> parseJson(JSONObject mMovieData) throws JSONException {
        List<Movie> movieList= new ArrayList<Movie>();
        try {
            int length = mMovieData.getJSONArray("results").length();
            for (int i=0; i< length; i++) {
                JSONObject movie = mMovieData.getJSONArray("results").getJSONObject(i);
                Log.d("@@@", "movie: " + movie);
                Movie film = new Movie();
                film.posterPath = movie.get("poster_path").toString();
                film.adult = movie.getBoolean("adult");
                film.overview = movie.get("overview").toString();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
                film.releaseDate = formatter.parse(movie.get("release_date").toString());
                film.genreIds = movie.get("genre_ids").toString();
                film.id = Integer.getInteger(movie.get("id").toString());
                film.originalTitle = movie.get("original_title").toString();
                film.originalLanguage = movie.get("original_language").toString();
                film.title = movie.get("title").toString();
                film.backdropPath = movie.get("backdrop_path").toString();
                film.popularity = movie.get("popularity").toString();
                film.voteCount = Integer.getInteger(movie.get("vote_count").toString());
                film.video = movie.getBoolean("video");
                film.voteAverage = movie.get("vote_average").toString();
                movieList.add(film);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log.d("@@@", "movieList" + movieList);
        return movieList;
    }

    public class Movie{
        private String posterPath;
        private Boolean adult;
        private String overview;
        private Date releaseDate;
        private String genreIds;
        private Integer id;
        private String originalTitle;
        private String originalLanguage;
        private String title;
        private String backdropPath;
        private String popularity;
        private Integer voteCount;
        private Boolean video;
        private String voteAverage;
    }
    
}


