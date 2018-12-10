package com.example.android.popularmoviesstage1;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.popularmoviesstage1.utilities.JsonUtils;
import com.example.android.popularmoviesstage1.utilities.NetworkUtils;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private RecyclerView mRecyclerView;
    private PosterAdapter mPosterAdapter;

    private String sortOrder;

    private TextView mErrorMessageDisplay;
    private ProgressBar mLoadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerview_posters);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        mPosterAdapter = new PosterAdapter();
        mRecyclerView.setAdapter(mPosterAdapter);

        sortOrder = getString(R.string.most_popular_key);

        mErrorMessageDisplay = findViewById(R.id.tv_error_message_display);
        mLoadingIndicator = findViewById(R.id.pb_loading_indicator);

        loadMovieData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuItemThatWasSelected = item.getItemId();

        if (menuItemThatWasSelected == R.id.action_most_popular) {
            sortOrder = getString(R.string.most_popular_key);
            loadMovieData();
        } else if (menuItemThatWasSelected == R.id.action_top_rated) {
            sortOrder = getString(R.string.top_rated_key);
            loadMovieData();
        }
        return true;
    }

    private void loadMovieData() {
        showMovieDataView();

        new FetchMovieTask().execute(sortOrder);
    }

    private void showMovieDataView() {
        /* First, make sure the error is invisible */
        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        /* Then, make sure the movie data is visible */
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void showErrorMessage() {
        /* First, hide the currently visible data */
        mRecyclerView.setVisibility(View.INVISIBLE);
        /* Then, show the error */
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
    }

    public class FetchMovieTask extends AsyncTask<String, Void, String[]> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected String[] doInBackground(String... strings) {

            if (strings.length == 0) {
                return null;
            }

            String sortOrder = strings[0];
            URL movieRequestUrl = NetworkUtils.buildUrl(sortOrder, MainActivity.this);
            Log.d(TAG, "Movie Request URL: " + movieRequestUrl);

            try {
                String jsonMovieResponse = NetworkUtils.getResponseFromHttpUrl(movieRequestUrl);

                String[] simpleJsonMovieData = JsonUtils.getMoviePostersFromJson(jsonMovieResponse);

                return simpleJsonMovieData;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String[] movieData) {
            mLoadingIndicator.setVisibility(View.INVISIBLE);
            if (movieData != null) {
                showMovieDataView();

                mPosterAdapter.setMovieData(movieData);
            } else {
                showErrorMessage();
            }

        }
    }
}
