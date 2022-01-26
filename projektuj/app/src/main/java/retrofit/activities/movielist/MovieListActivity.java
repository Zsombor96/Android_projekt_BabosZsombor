package retrofit.activities.movielist;

import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import edu.uci.swe264p.retrofit.R;
import retrofit.data.Movie;
import retrofit.data.MovieApiClient;
import retrofit.data.MovieList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class MovieListActivity extends AppCompatActivity {

    private static final String TAG = MovieListActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        getMovieList();
    }

    private void getMovieList() {
        MovieApiClient.getMovieList().enqueue(movieListCallback);
    }

    private final Callback<MovieList> movieListCallback = new Callback<MovieList>() {

        @Override
        public void onResponse(@NonNull Call<MovieList> call, Response<MovieList> response) {
            assert response.body() != null;
            final List<Movie> movieList = response.body().getMovieList();

            Log.i(TAG, "MovieList size = " + movieList.size());
            Log.i(TAG, "Top movie = " + movieList.get(0).getTitle());

            RecyclerView recyclerView = findViewById(R.id.rvMovieList);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(MovieListActivity.this));
            recyclerView.setAdapter(new MovieListAdapter(movieList));
        }

        @Override
        public void onFailure(@NonNull Call<MovieList> call, Throwable throwable) {
            Log.e(TAG, throwable.toString());
        }
    };
}
