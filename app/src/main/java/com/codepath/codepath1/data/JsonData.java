package com.codepath.codepath1.data;

import android.util.Log;

import com.codepath.codepath1.models.Movie;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class JsonData {

    public ArrayList<Movie> getMovieList(JSONObject mMovieData) throws JSONException {
        ArrayList<Movie> movieList = new ArrayList<>();
        try {
            int length = mMovieData.getJSONArray("results").length();
            for (int i=0; i< length; i++) {
                JSONObject movie = mMovieData.getJSONArray("results").getJSONObject(i);
                Log.d("@@@", "movie: " + movie);
                Movie film = new Movie(
                    movie.get("original_title").toString(),
                    movie.get("overview").toString(),
                    movie.get("poster_path").toString(),
                    movie.get("backdrop_path").toString(),
                    movie.get("popularity").toString(),
                    movie.get("vote_average").toString());
                movieList.add(film);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("@@@", "movieList" + movieList);
        return movieList;
    }

}


