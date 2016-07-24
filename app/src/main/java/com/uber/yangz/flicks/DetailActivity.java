package com.uber.yangz.flicks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Movie movie = (Movie) getIntent().getSerializableExtra("movie");
        TextView tvTitle = (TextView) findViewById(R.id.detailTitle);
        TextView tvOverview = (TextView) findViewById(R.id.detailOverview);
        RatingBar rbRating = (RatingBar) findViewById(R.id.ratingBar);
        ImageView ivImage = (ImageView) findViewById(R.id.moviePreview);


        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());
        // hard code for now
        rbRating.setProgress(5);
        Picasso.with(this).
                load(movie.getBackdropUriString()).
                placeholder(R.drawable.popcorn_bg).
                into(ivImage);
    }
}
