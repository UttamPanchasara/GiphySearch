package com.uttampanchasara.baseprojectkotlin.ui.dashboard.home

import com.uttampanchasara.baseprojectkotlin.data.repository.videos.Data
import com.uttampanchasara.baseprojectkotlin.ui.base.BaseView

interface HomeView : BaseView {
    fun onGetVideos(data: List<Data>)
}