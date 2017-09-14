package com.codepath.codepath1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.codepath1.models.Movie;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.codepath.codepath1.MainActivity.EXTRA_MESSAGE;

public class MovieActivity extends AppCompatActivity {

    @BindView(R.id.movie_title) TextView mMovieTitle;
    @BindView(R.id.movie_description) TextView mMovieDescription;
    @BindView(R.id.movie_image) ImageView mMovieImage;
    @BindView(R.id.movie_rating) TextView mMovieRating;
    @BindView(R.id.movie_popularity) TextView mMoviePopularity;

    Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        ButterKnife.bind(this);

        Bundle b = getIntent().getExtras();
        if (b != null) {
            movie = b.getParcelable(EXTRA_MESSAGE);
            if (movie != null) {
                String imageUri = "https://image.tmdb.org/t/p/w500" + movie.posterPath;

                Picasso.with(getApplicationContext()).load(imageUri).into(mMovieImage);
                mMovieTitle.setText(movie.title);
                mMovieDescription.setText(movie.overview);
                mMovieRating.setText(movie.voteAverage);
                mMoviePopularity.setText(movie.popularity);
            }
        }
    }
}
