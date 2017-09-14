package com.codepath.codepath1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.codepath.codepath1.adapters.CustomMovieAdapter;
import com.codepath.codepath1.data.JsonData;
import com.codepath.codepath1.data.MovieDataRequestHandler;
import com.codepath.codepath1.models.Movie;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.movieItem) ListView mMovieItemList;

    public static final String EXTRA_MESSAGE = "com.codepath.codepath1.MESSAGE";
    ArrayList<Movie> mMovieList;
    JsonData json = new JsonData();

    private MovieDataRequestHandler mMovieDataRequestHandler = new MovieDataRequestHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mMovieDataRequestHandler.getMovieData(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject responseJSON) {
                try {
                    mMovieList = json.getMovieList(responseJSON);
                    if (mMovieList != null) {
                        populateUsersList(mMovieList);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject jsonObject) {
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

    private void populateUsersList(ArrayList<Movie> movieList) {
        // Create the adapter to convert the array to views
        CustomMovieAdapter adapter = new CustomMovieAdapter(this, movieList);
        // Attach the adapter to a ListView
        mMovieItemList.setAdapter(adapter);

        mMovieItemList.setOnItemClickListener(
            new ListView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Movie movie = (Movie) parent.getItemAtPosition(position);
                    Log.d("@@@", movie.title + " " + movie.posterPath);
                    Intent intent = new Intent(getApplicationContext(), MovieActivity.class);
                    intent.putExtra(EXTRA_MESSAGE, movie);
                    startActivity(intent);
                }


            });
    }
}