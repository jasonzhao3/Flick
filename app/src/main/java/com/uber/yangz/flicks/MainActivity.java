package com.uber.yangz.flicks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    String apiKey = "a07e22bc18f5cb106bfe4cc1f83ad8ed";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MoviesAdaptor adapter = setupMovieListWithAdapter();
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("api_key", apiKey);
        client.get("https://api.themoviedb.org/3/movie/now_playing", params, new TextHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String res) {
                        loadMovies(adapter, res);
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                        // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                    }
                }
        );
    }

    private MoviesAdaptor setupMovieListWithAdapter() {
        ArrayList<Movie> moviesArray = new ArrayList<Movie>();
        MoviesAdaptor adapter = new MoviesAdaptor(this, moviesArray);
        ListView listView = (ListView) findViewById(R.id.movieList);
        listView.setAdapter(adapter);
        return adapter;
    }

    private void loadMovies(MoviesAdaptor adapter, String response) {
        MoviesResponse moviesResponse = MoviesResponse.parseJSON(response);
        adapter.addAll(moviesResponse.getMovies());
    }
}
