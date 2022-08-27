package com.maxlab.darkmusic;

public class SongsModel {

    private String songId, movieName, singerName, songImage, songName, songUrl;

    public SongsModel(String songId, String movieName, String singerName, String songImage, String songName, String songUrl) {
        this.songId = songId;
        this.movieName = movieName;
        this.singerName = singerName;
        this.songImage = songImage;
        this.songName = songName;
        this.songUrl = songUrl;
    }

    public SongsModel() {
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

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public String getSongImage() {
        return songImage;
    }

    public void setSongImage(String songImage) {
        this.songImage = songImage;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSongUrl() {
        return songUrl;
    }

    public void setSongUrl(String songUrl) {
        this.songUrl = songUrl;
    }
}
