package com.maxlab.darkmusic.argmusicplayer.Callbacks;

import com.maxlab.darkmusic.argmusicplayer.Models.ArgAudio;

public interface OnPlaylistUpdateListener {
    void onUpdate(ArgAudio audio, boolean wasRemoved);
}
