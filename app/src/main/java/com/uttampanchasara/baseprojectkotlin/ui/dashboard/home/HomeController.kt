package com.uttampanchasara.baseprojectkotlin.ui.dashboard.home

import android.util.Log
import android.view.View
import com.uttampanchasara.baseprojectkotlin.R
import com.uttampanchasara.baseprojectkotlin.data.repository.videos.Data
import com.uttampanchasara.baseprojectkotlin.ui.base.BaseController
import javax.inject.Inject

class HomeController : BaseController(), HomeView {
    override fun getLayoutId(): Int = R.layout.controller_home

    @Inject
    lateinit var mPresenter: HomePresenter

    override fun setupView(view: View) {
        getActivityComponent()?.inject(this)
        mPresenter.onAttachView(this)
        mPresenter.getGiphyVideos("funny", 0)
    }

    override fun onGetVideos(data: List<Data>) {
        Log.i("Home", "Size: " + data.size)
    }
}