package com.maxlab.darkmusic.argmusicplayer.Callbacks;

import com.maxlab.darkmusic.argmusicplayer.Models.ArgAudioList;

public interface OnPlaylistAudioChangedListener {
    void onPlaylistAudioChanged(ArgAudioList playlist, int currentAudioIndex);
}
