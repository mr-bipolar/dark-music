package com.maxlab.darkmusic.argmusicplayer.Callbacks;

import com.maxlab.darkmusic.argmusicplayer.Models.ArgAudioList;

public interface OnPlaylistStateChangedListener {
    void onPlaylistStateChanged(boolean isPlaylist, ArgAudioList playlist);
}
