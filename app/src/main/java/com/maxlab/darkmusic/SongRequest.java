package com.maxlab.darkmusic;

public class SongRequest {
    private String userName, songInfo;

    public SongRequest(String userName, String songInfo) {
        this.userName = userName;
        this.songInfo = songInfo;
    }

    public SongRequest() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSongInfo() {
        return songInfo;
    }

    public void setSongInfo(String songInfo) {
        this.songInfo = songInfo;
    }
}
