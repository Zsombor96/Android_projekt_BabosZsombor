package retrofit.data;

import com.google.gson.annotations.SerializedName;

public class Movie {

    // ImageView ivMovie
    @SerializedName("poster_path")
    private String posterPath;

    // TextView tvTitle
    @SerializedName("title")
    private String title;

    // TextView tvReleaseDate
    @SerializedName("release_date")
    private String releaseDate;

    // TextView tvVote
    @SerializedName("vote_average")
    private Float voteAverage;

    // TextView tvOverview
    @SerializedName("overview")
    private String overview;

    public Movie(Float voteAverage, String posterPath, String title, String releaseDate, String overview) {
        this.voteAverage = voteAverage;
        this.posterPath = posterPath;
        this.title = title;
        this.releaseDate = releaseDate;
        this.overview = overview;
    }

    public Float getVoteAverage() {
        return voteAverage;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getTitle() {
        return title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getOverview() {
        return overview;
    }
}
