package com.uttampanchasara.baseprojectkotlin.ui.dashboard.home

import android.content.Intent
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.ViewFlipper
import butterknife.BindView
import com.uttampanchasara.baseprojectkotlin.R
import com.uttampanchasara.baseprojectkotlin.data.repository.videos.Data
import com.uttampanchasara.baseprojectkotlin.ui.base.BaseController
import com.uttampanchasara.baseprojectkotlin.ui.videoplayback.VideoPlaybackActivity
import com.uttampanchasara.baseprojectkotlin.utils.EndlessRecyclerViewScrollListener
import javax.inject.Inject


class HomeController : BaseController(), HomeView, VideosAdapter.VideoClickListener, SearchView.OnQueryTextListener {

    override fun getLayoutId(): Int = R.layout.controller_home

    @Inject
    lateinit var mPresenter: HomePresenter

    @BindView(R.id.rootView)
    lateinit var mRootView: ConstraintLayout

    @BindView(R.id.viewFlipper)
    lateinit var mViewFlipper: ViewFlipper

    @BindView(R.id.rvVideos)
    lateinit var mRvVideos: RecyclerView

    private lateinit var mAdapter: VideosAdapter

    private var mVideoList: MutableList<Data> = mutableListOf()
    private var searchQuery = ""

    private lateinit var mScrollListener: EndlessRecyclerViewScrollListener

    override fun setupView(view: View) {
        getActivityComponent()?.inject(this)
        mPresenter.onAttachView(this)

        activity?.let {
            mAdapter = VideosAdapter(it, this)
            val layoutManager = GridLayoutManager(it, 3)
            mRvVideos.layoutManager = layoutManager
            mRvVideos.adapter = mAdapter

            mScrollListener = object : EndlessRecyclerViewScrollListener(layoutManager) {
                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                    // Triggered only when new data needs to be appended to the list
                    // Add whatever code is needed to append new items to the bottom of the list
                    loadDataFromApi(totalItemsCount)
                }
            }

            mRvVideos.addOnScrollListener(mScrollListener)

            resetStatesAndSearch("")
        }

        setHasOptionsMenu(true)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)

        val searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.queryHint = "Search videos"
        searchView.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let {
            resetStatesAndSearch(it)
        }
        return true
    }

    private fun resetStatesAndSearch(query: String) {
        mVideoList.clear()
        mScrollListener.resetState()
        searchQuery = query
        mAdapter.notifyDataSetChanged()
        if (query.isNotEmpty()) {
            hideKeyboard(mRootView)
            loadDataFromApi(0)
        } else {
            mViewFlipper.displayedChild = 1
        }
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        //do nothing
        return true
    }

    fun loadDataFromApi(offset: Int) {
        if (mVideoList.isEmpty()) {
            mViewFlipper.displayedChild = 0
        }
        mPresenter.getGiphyVideos(searchQuery, offset)
    }

    override fun onGetVideos(data: List<Data>) {
        if (data.isNotEmpty()) {
            mViewFlipper.displayedChild = 2
            mVideoList.addAll(data)
            Log.i("Home", "Size: " + mVideoList.size)
            mAdapter.setVideoList(mVideoList)
        } else {
            mViewFlipper.displayedChild = 1
        }
    }

    override fun onVideoSelected(video: Data) {
        val intent = Intent(activity, VideoPlaybackActivity::class.java)
        intent.putExtra(VideoPlaybackActivity.DATA, video)
        startActivity(intent)
    }
}