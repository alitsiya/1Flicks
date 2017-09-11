package com.codepath.codepath1;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private List<JsonData.Movie> movieList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView text = (TextView) findViewById(R.id.movieTitle);
        WebRequest.getMovieData(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject responseJSON) {
                JsonData movieData = new JsonData();
                try {
                    movieList = movieData.parseJson(responseJSON);
                    Log.d("@@@", "onSuccess movieList " + movieList);
                    text.setText(movieList.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Context context = getApplicationContext();
                CharSequence text = "Error on loading movies";
                int duration = Toast.LENGTH_SHORT;
                Toast.makeText(context, text, duration).show();
            }

            @Override
            public void onFinish() {
                Log.d("@@@", "onFinish movieList " + movieList);
            }
        });
    }
}

final class WebRequest {
    private static AsyncHttpClient mClient = new AsyncHttpClient();

    public static void getMovieData(JsonHttpResponseHandler handler) {
        mClient.post("https://api.themoviedb.org/3/movie/now_playing?api_key=", handler);
    }
}