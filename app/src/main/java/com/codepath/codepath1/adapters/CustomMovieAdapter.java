package com.codepath.codepath1.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.codepath1.R;
import com.codepath.codepath1.models.Movie;
import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

import java.util.List;

public class CustomMovieAdapter extends ArrayAdapter<Movie>  {

    private Context mContext;

    private static class ViewHolder {
        TextView title;
        TextView overview;
        ImageView image;
    }

    public CustomMovieAdapter(Context context, List<Movie> movies) {
        super(context, R.layout.item_movie, movies);
        mContext = context;
    }

    @Override
    public @NonNull View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // Get the data item for this position
        Movie movie = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            // If there's no view to re-use, inflate a brand new view for row
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.item_movie, parent, false);
            // Lookup view for data population
            viewHolder.title = (TextView) convertView.findViewById(R.id.movieTitle);
            viewHolder.overview = (TextView) convertView.findViewById(R.id.movieDescription);
            // Populate the data into the template view using the data object
            String imageUri = getImageUrl(movie);

            viewHolder.image = (ImageView) convertView.findViewById(R.id.movieImage);
            Picasso.with(mContext).load(imageUri)
                .fit().centerCrop()
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .transform(new RoundedCornersTransformation(10, 10))
                .into(viewHolder.image);

            convertView.setTag(viewHolder);
        } else {
            // View is being recycled, retrieve the viewHolder object from tag
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data from the data object via the viewHolder object
        // into the template view.
        viewHolder.title.setText(movie.title);
        viewHolder.overview.setText(movie.overview);
        Picasso.with(mContext)
            .load(getImageUrl(movie))
            .fit().centerCrop()
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.error)
            .tag(mContext)
            .into(viewHolder.image);
        // Return the completed view to render on screen
        return convertView;
    }

    private String getImageUrl(Movie movie) {
        int orientation = mContext.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            return "https://image.tmdb.org/t/p/w500" + movie.backDropPath;
        } else {
            return "https://image.tmdb.org/t/p/w500" + movie.posterPath;
        }
    }
}
