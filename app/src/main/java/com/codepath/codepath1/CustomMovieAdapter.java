package com.codepath.codepath1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomMovieAdapter extends ArrayAdapter<JsonData.Movie>  {
    public CustomMovieAdapter(Context context, ArrayList<JsonData.Movie> movies) {
        super(context, 0, movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        JsonData.Movie movie = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_movie, parent, false);
        }
        // Lookup view for data population
        TextView movieName = (TextView) convertView.findViewById(R.id.movieTitle);
        TextView movieDescription = (TextView) convertView.findViewById(R.id.movieDescription);
        // Populate the data into the template view using the data object
        movieName.setText(movie.title);
        movieDescription.setText(movie.overview);
        // Return the completed view to render on screen
        return convertView;
    }
}
