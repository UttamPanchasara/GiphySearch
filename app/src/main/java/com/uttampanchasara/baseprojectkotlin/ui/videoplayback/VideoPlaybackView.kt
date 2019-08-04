package com.uttampanchasara.baseprojectkotlin.ui.videoplayback

import com.uttampanchasara.baseprojectkotlin.data.repository.favvideos.FavoriteVideos
import com.uttampanchasara.baseprojectkotlin.ui.base.BaseView

interface VideoPlaybackView : BaseView {
    fun onVotesUpdated()
    fun onVotesAvailable(it: List<FavoriteVideos>)
}