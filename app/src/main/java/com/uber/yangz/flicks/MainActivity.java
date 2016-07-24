package com.uber.yangz.flicks;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private static final String API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed";
    private SwipeRefreshLayout swipeContainer;
    private MoviesAdaptor adapter;
    private AsyncHttpClient client;
    private static int pageIndex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupSwipeContainer();
        setupMovieListWithAdapter();
        client = new AsyncHttpClient();

        pageIndex = 1;
        fetchMoviesAsync(pageIndex);

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                pageIndex += 1;
                System.out.println(pageIndex);
                fetchMoviesAsync(pageIndex);
            }
        });
    }

    private void setupMovieListWithAdapter() {
        ArrayList<Movie> moviesArray = new ArrayList<>();
        adapter = new MoviesAdaptor(this, moviesArray);
        ListView listView = (ListView) findViewById(R.id.movieList);
        listView.setAdapter(adapter);
    }

    private void setupSwipeContainer() {
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        swipeContainer.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
    }

    private void fetchMoviesAsync(int pageIndex) {
        RequestParams params = new RequestParams();
        params.put("api_key", API_KEY);
        params.put("page", pageIndex);
        client.get("https://api.themoviedb.org/3/movie/now_playing", params, new TextHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String res) {
                        loadMovies(adapter, res);
                        swipeContainer.setRefreshing(false);
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                        Log.d("DEBUG", "Fetch movies error: " + t.toString());

                    }
                }
        );
    }

    private void loadMovies(MoviesAdaptor adapter, String response) {
        MoviesResponse moviesResponse = MoviesResponse.parseJSON(response);
        adapter.clear();
        adapter.addAll(moviesResponse.getMovies());
    }
}
