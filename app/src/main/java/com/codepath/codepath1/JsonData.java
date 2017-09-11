package com.codepath.codepath1;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class JsonData {

    public ArrayList<Movie> getMovieList(JSONObject mMovieData) throws JSONException {
        ArrayList<Movie> movieList = new ArrayList<>();
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

        public String posterPath;
        public Boolean adult;
        public String overview;
        public Date releaseDate;
        public String genreIds;
        public Integer id;
        public String originalTitle;
        public String originalLanguage;
        public String title;
        public String backdropPath;
        public String popularity;
        public Integer voteCount;
        public Boolean video;
        public String voteAverage;
        public Movie(){
            super();
        }

        public Movie(String posterPath,
                    Boolean adult,
                    String overview,
                    Date releaseDate,
                    String genreIds,
                    int id,
                    String originalTitle,
                    String originalLanguage,
                    String title,
                    String backdropPath,
                    String popularity,
                    int voteCount,
                    Boolean video,
                    String voteAverage) {
            super();
            this.posterPath = posterPath;
            this.adult = adult;
            this.overview = overview;
            this.releaseDate = releaseDate;
            this.genreIds = genreIds;
            this.id = id;
            this.originalTitle = originalTitle;
            this.originalLanguage = originalLanguage;
            this.title = title;
            this.backdropPath = backdropPath;
            this.popularity = popularity;
            this.voteCount = voteCount;
            this.video = video;
            this.voteAverage = voteAverage;
        }
    }


    
}


