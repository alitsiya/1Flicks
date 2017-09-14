package com.codepath.codepath1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.codepath1.models.Movie;
import com.squareup.picasso.Picasso;

import static com.codepath.codepath1.MainActivity.EXTRA_MESSAGE;

public class MovieActivity extends AppCompatActivity {

    Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        Bundle b = getIntent().getExtras();
        if (b != null) {
            movie = b.getParcelable(EXTRA_MESSAGE);
            if (movie != null) {
                String imageUri = "https://image.tmdb.org/t/p/w500" + movie.posterPath;
                TextView movieName = (TextView) findViewById(R.id.movie_title);
                TextView movieDescription = (TextView) findViewById(R.id.movie_description);
                ImageView movieImage = (ImageView) findViewById(R.id.movie_image);
                TextView movieRating = (TextView) findViewById(R.id.movie_rating);
                TextView moviePopularity = (TextView) findViewById(R.id.movie_popularity);

                Picasso.with(getApplicationContext()).load(imageUri).into(movieImage);
                movieName.setText(movie.title);
                movieDescription.setText(movie.overview);
                movieRating.setText(movie.voteAverage);
                moviePopularity.setText(movie.popularity);
            }
        }

    }
}
