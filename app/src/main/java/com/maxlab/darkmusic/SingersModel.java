package com.maxlab.darkmusic;

public class SingersModel {

    private String singerId, singerName, singerImage;

    public SingersModel() {
    }

    public SingersModel(String singerId, String singerName, String singerImage) {
        this.singerId = singerId;
        this.singerName = singerName;
        this.singerImage = singerImage;
    }

    public String getSingerId() {
        return singerId;
    }

    public void setSingerId(String singerId) {
        this.singerId = singerId;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public String getSingerImage() {
        return singerImage;
    }

    public void setSingerImage(String singerImage) {
        this.singerImage = singerImage;
    }
}
