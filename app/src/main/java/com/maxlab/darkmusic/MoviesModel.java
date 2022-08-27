package com.maxlab.darkmusic;

public class MoviesModel {
    private String movieId, movieName, movieImage;

    public MoviesModel(String movieId, String movieName, String movieImage) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.movieImage = movieImage;
    }

    public MoviesModel() {
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieImage() {
        return movieImage;
    }

    public void setMovieImage(String movieImage) {
        this.movieImage = movieImage;
    }
}
