package com.maxlab.darkmusic;

public class SingerSongModel {
    private String songId, movieName,songName, songImage, songUrl;

    public SingerSongModel(String songId, String movieName, String songName, String songImage, String songUrl) {
        this.songId = songId;
        this.movieName = movieName;
        this.songName = songName;
        this.songImage = songImage;
        this.songUrl = songUrl;
    }

    public SingerSongModel() {
    }

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSongImage() {
        return songImage;
    }

    public void setSongImage(String songImage) {
        this.songImage = songImage;
    }

    public String getSongUrl() {
        return songUrl;
    }

    public void setSongUrl(String songUrl) {
        this.songUrl = songUrl;
    }
}
