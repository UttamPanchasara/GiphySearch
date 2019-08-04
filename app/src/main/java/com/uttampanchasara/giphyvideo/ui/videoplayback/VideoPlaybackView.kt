package com.uttampanchasara.giphyvideo.ui.videoplayback

import com.uttampanchasara.giphyvideo.data.repository.favvideos.FavoriteVideos
import com.uttampanchasara.giphyvideo.ui.base.BaseView

interface VideoPlaybackView : BaseView {
    fun onVotesUpdated()
    fun onVotesAvailable(it: List<FavoriteVideos>)
}