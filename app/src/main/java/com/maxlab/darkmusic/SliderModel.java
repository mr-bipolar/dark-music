package com.maxlab.darkmusic;

public class SliderModel {

    private String sliderId, sliderImage, sliderName;

    public SliderModel(String sliderId, String sliderImage, String sliderName) {
        this.sliderId = sliderId;
        this.sliderImage = sliderImage;
        this.sliderName = sliderName;
    }

    public SliderModel() {
    }

    public String getSliderId() {
        return sliderId;
    }

    public void setSliderId(String sliderId) {
        this.sliderId = sliderId;
    }

    public String getSliderImage() {
        return sliderImage;
    }

    public void setSliderImage(String sliderImage) {
        this.sliderImage = sliderImage;
    }

    public String getSliderName() {
        return sliderName;
    }

    public void setSliderName(String sliderName) {
        this.sliderName = sliderName;
    }

}
