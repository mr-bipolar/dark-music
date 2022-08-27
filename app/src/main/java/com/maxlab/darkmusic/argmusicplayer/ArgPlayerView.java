package com.maxlab.darkmusic.argmusicplayer;

import android.content.Context;
import android.util.AttributeSet;

import com.maxlab.darkmusic.argmusicplayer.Models.ArgAudio;
import com.maxlab.darkmusic.argmusicplayer.Models.ArgAudioList;


public class ArgPlayerView extends ArgPlayerViewRoot {
    @Override
    protected void setEmbeddedImageBitmap(byte[] byteArray) {
    }

    @Override
    protected void onAudioNameChanged(ArgAudio audio) {

    }

    @Override
    protected void onPlaylistAudioChanged(ArgAudioList list) {

    }
    public ArgPlayerView(Context context) {
        super(context);
    }

    public ArgPlayerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ArgPlayerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void init(Context context, int layoutResId) {
        super.init(context, layoutResId);
    }
}
