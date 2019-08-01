package com.uttampanchasara.baseprojectkotlin.ui.dashboard.home

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.ViewFlipper
import butterknife.BindView
import com.uttampanchasara.baseprojectkotlin.R
import com.uttampanchasara.baseprojectkotlin.data.repository.videos.Data
import com.uttampanchasara.baseprojectkotlin.ui.base.BaseController
import javax.inject.Inject

class HomeController : BaseController(), HomeView {
    override fun getLayoutId(): Int = R.layout.controller_home

    @Inject
    lateinit var mPresenter: HomePresenter

    @BindView(R.id.viewFlipper)
    lateinit var mViewFlipper: ViewFlipper

    @BindView(R.id.rvVideos)
    lateinit var mRvVideos: RecyclerView

    private lateinit var mAdapter: VideosAdapter

    override fun setupView(view: View) {
        getActivityComponent()?.inject(this)
        mPresenter.onAttachView(this)
        mPresenter.getGiphyVideos("funny", 0)
        mViewFlipper.displayedChild = 0

        activity?.let {
            mAdapter = VideosAdapter(it)
            mRvVideos.layoutManager = GridLayoutManager(it, 3)
            mRvVideos.adapter = mAdapter
        }
    }

    override fun onGetVideos(data: List<Data>) {
        Log.i("Home", "Size: " + data.size)
        if (data.isNotEmpty()) {
            mViewFlipper.displayedChild = 2
            mAdapter.setVideoList(data)
        } else {
            mViewFlipper.displayedChild = 1
        }
    }
}