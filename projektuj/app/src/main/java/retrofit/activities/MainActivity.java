package retrofit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import edu.uci.swe264p.retrofit.R;
import retrofit.activities.movielist.MovieListActivity;
import retrofit.data.Movie;
import retrofit.data.MovieApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private static final int ID_THE_MATRIX = 603;
    private static final int ID_THE_SHAWSHANK_REDEMPTION = 278;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getDemoMovie();
        setupButtons();
    }

    private void getDemoMovie() {
        MovieApiClient.getMovie(ID_THE_SHAWSHANK_REDEMPTION)
                .enqueue(movieCallback);
    }

    private final Callback<Movie> movieCallback = new Callback<Movie>() {

        @Override
        public void onResponse(@NonNull Call<Movie> call, Response<Movie> response) {
            int[] ids = {
                    R.id.txtTitle,
                    R.id.txtReleaseDate,
                    R.id.txtPoster,
                    R.id.txtVote,
                    R.id.txtOverview
            };

            Movie movie = response.body();
            assert movie != null;
            String[] values = {
                    movie.getTitle(),
                    movie.getReleaseDate(),
                    movie.getPosterPath(),
                    movie.getVoteAverage().toString(),
                    movie.getOverview()
            };

            for (int i = 0; i < ids.length; i++) {
                ((TextView) findViewById(ids[i])).setText(values[i]);
            }
        }

        @Override
        public void onFailure(@NonNull Call<Movie> call, Throwable throwable) {
            Log.e(TAG, throwable.toString());
        }
    };

    private void setupButtons() {

        final Button btnMovieList = findViewById(R.id.btnMovieList);
        btnMovieList.setOnClickListener(view -> startActivity(
                new Intent(getApplicationContext(), MovieListActivity.class)));

    }
}
