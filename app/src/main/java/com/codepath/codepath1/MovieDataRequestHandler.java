package com.codepath.codepath1;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

public class MovieDataRequestHandler {
    private final String URL = "https://api.themoviedb.org/3/movie/now_playing";
    private final String API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed";

    public void getMovieData(JsonHttpResponseHandler handler) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.post(URL + "?api_key=" + API_KEY, handler);
    }
}
