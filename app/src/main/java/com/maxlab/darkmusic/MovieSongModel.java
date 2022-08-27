package com.maxlab.darkmusic;

public class MovieSongModel {

    private String songId, songName, songUrl, songImage;

    public MovieSongModel(String songId, String songName, String songUrl, String songImage) {
        this.songId = songId;
        this.songName = songName;
        this.songUrl = songUrl;
        this.songImage = songImage;
    }

    public MovieSongModel() {
    }

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
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

    public String getSongImage() {
        return songImage;
    }

    public void setSongImage(String songImage) {
        this.songImage = songImage;
    }
}
