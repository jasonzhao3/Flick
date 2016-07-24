package com.uber.yangz.flicks;

import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by yangz on 7/24/16.
 */
public class MoviesAdaptor extends ArrayAdapter<Movie> {
    public MoviesAdaptor(Context context, ArrayList<Movie> movies) {
        super(context, 0, movies);
    }

    private static class ViewHolder {
        TextView title;
        TextView overview;
        ImageView image;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Movie movie = getItem(position);

        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.movie_item, parent, false);
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            viewHolder.overview = (TextView) convertView.findViewById(R.id.overview);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.image);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.title.setText(movie.getTitle());
        viewHolder.overview.setText(movie.getOverview());

        int orientation = getContext().getResources().getConfiguration().orientation;
        String imageUri = null;
        int width = 0, height = 0;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            imageUri = movie.getPosterUriString();
            width = 450;
            height = 800;
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            imageUri = movie.getBackdropUriString();
            width = 800;
            height = 450;
        }

        Picasso.with(this.getContext()).
                load(imageUri).
                placeholder(R.drawable.popcorn_bg).
                resize(width, height).
                centerCrop().
                into(viewHolder.image);

        return convertView;
    }

}
