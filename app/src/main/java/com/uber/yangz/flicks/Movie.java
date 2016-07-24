package com.uber.yangz.flicks;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by yangz on 7/24/16.
 */
public class Movie implements Serializable {
    private static String IMAGE_URI_PREFIX = "https://image.tmdb.org/t/p/w342";

    private String title;
    private String overview;

    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("backdrop_path")
    private String backdropPath;

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterUriString() {
        return IMAGE_URI_PREFIX + posterPath;
    }

    public String getBackdropUriString() {
        return IMAGE_URI_PREFIX + backdropPath;
    }
}
