package com.codepath.codepath1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

class CustomMovieAdapter extends ArrayAdapter<Movie>  {

    private Context mContext;

    CustomMovieAdapter(Context context, ArrayList<Movie> movies) {
        super(context, 0, movies);
        mContext = context;
    }

    @Override
    public @NonNull View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // Get the data item for this position
        Movie movie = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_movie, parent, false);
        }
        // Lookup view for data population
        TextView movieName = (TextView) convertView.findViewById(R.id.movieTitle);
        TextView movieDescription = (TextView) convertView.findViewById(R.id.movieDescription);
        // Populate the data into the template view using the data object
        String imageUri = "https://image.tmdb.org/t/p/w500" + movie.posterPath;
        ImageView movieImage = (ImageView) convertView.findViewById(R.id.movieImage);
        Picasso.with(mContext).load(imageUri).into(movieImage);
        movieName.setText(movie.title);
        movieDescription.setText(movie.overview);
        // Return the completed view to render on screen
        return convertView;
    }
}
