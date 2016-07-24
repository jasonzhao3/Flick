package com.uber.yangz.flicks;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangz on 7/24/16.
 */
public class MoviesResponse {
    @SerializedName("results")
    private List<Movie> movies;

    // public constructor is necessary for collections
    public MoviesResponse() {
        movies = new ArrayList<Movie>();
    }

    public static MoviesResponse parseJSON(String response) {
        Gson gson = new GsonBuilder().create();
        MoviesResponse moviesResponse = gson.fromJson(response, MoviesResponse.class);
        return moviesResponse;
    }

    public List<Movie> getMovies() {
        return movies;
    }
}