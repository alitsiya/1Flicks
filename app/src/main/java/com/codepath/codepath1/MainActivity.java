package com.codepath.codepath1;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<JsonData.Movie> mMovieList;
    JsonData json = new JsonData();

    private MovieDataRequestHandler mMovieDataRequestHandler = new MovieDataRequestHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMovieDataRequestHandler.getMovieData(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject responseJSON) {
                try {
                    mMovieList = json.getMovieList(responseJSON);
                    Log.d("@@@", "onSuccess movieList " + mMovieList);
                    if (mMovieList != null) {
                        populateUsersList(mMovieList);
                    }
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
                Log.d("@@@", "onFinish movieList " + mMovieList);
            }
        });
    }
    private void populateUsersList(ArrayList<JsonData.Movie> movieList) {
        // Create the adapter to convert the array to views
        CustomMovieAdapter adapter = new CustomMovieAdapter(this, movieList);
        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.movieItem);
        listView.setAdapter(adapter);
    }
}