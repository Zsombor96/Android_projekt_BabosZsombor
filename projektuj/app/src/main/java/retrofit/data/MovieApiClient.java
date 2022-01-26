package retrofit.data;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieApiClient {

    private MovieApiClient() {
    }

    private static final String BASE_URL = "https://api.themoviedb.org/3/";

    private static final String API_KEY = "ba8f1d4d4a7153ec4d56cff72c5a37aa";

    private static final MovieApiService SERVICE =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(MovieApiService.class);

    public static Call<Movie> getMovie(int id) {
        return SERVICE.getMovie(id, API_KEY);
    }

    public static Call<MovieList> getMovieList() {
        return SERVICE.getMovieList(API_KEY);
    }
}
