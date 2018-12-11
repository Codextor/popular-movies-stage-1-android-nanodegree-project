package com.example.android.popularmoviesstage1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import static com.example.android.popularmoviesstage1.MainActivity.jsonMovieResponse;
import static com.example.android.popularmoviesstage1.MainActivity.simpleJsonMovieData;
import static com.example.android.popularmoviesstage1.utilities.JsonUtils.getMovieDetailsFromJson;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView mTitleText = findViewById(R.id.tv_title);
        ImageView mPosterImage = findViewById(R.id.iv_poster);
        TextView mReleaseText = findViewById(R.id.tv_release);
        TextView mRatingText = findViewById(R.id.tv_rating);
        TextView mSynopsisText = findViewById(R.id.tv_synopsis);
        JSONObject movieDetails;

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int posterWidth = (int) (width / 2.4);

        Intent intentThatStartedThisActivity = getIntent();
        if (intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)) {

            int position = intentThatStartedThisActivity.getIntExtra(Intent.EXTRA_TEXT, 0);

            try {
                movieDetails = getMovieDetailsFromJson(jsonMovieResponse, position);
                mTitleText.setText(movieDetails.getString("title"));

                String posterForThisMovie = simpleJsonMovieData[position];
                Picasso.get().load(posterForThisMovie).resize(posterWidth, 0).into(mPosterImage);

                String releaseYear = movieDetails.getString("release_date").substring(0, 4);
                mReleaseText.setText(releaseYear);

                String rating = movieDetails.getString("vote_average") + "/10";
                mRatingText.setText(rating);

                mSynopsisText.setText(movieDetails.getString("overview"));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
