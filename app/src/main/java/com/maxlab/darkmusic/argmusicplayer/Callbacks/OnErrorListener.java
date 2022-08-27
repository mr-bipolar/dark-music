package com.maxlab.darkmusic.argmusicplayer.Callbacks;

import com.maxlab.darkmusic.argmusicplayer.Enums.ErrorType;

public interface OnErrorListener {
    void onError(ErrorType errorType, String description);
}
