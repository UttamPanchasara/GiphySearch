package com.uttampanchasara.giphyvideo.ui.dashboard.home

import com.uttampanchasara.giphyvideo.data.repository.videos.Data
import com.uttampanchasara.giphyvideo.ui.base.BaseView

interface HomeView : BaseView {
    fun onGetVideos(data: List<Data>)
}