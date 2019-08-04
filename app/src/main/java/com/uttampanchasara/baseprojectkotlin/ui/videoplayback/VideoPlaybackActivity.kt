package com.uttampanchasara.baseprojectkotlin.ui.videoplayback

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.view.View
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.uttampanchasara.baseprojectkotlin.R
import com.uttampanchasara.baseprojectkotlin.data.repository.favvideos.FavoriteVideos
import com.uttampanchasara.baseprojectkotlin.data.repository.videos.Data
import com.uttampanchasara.baseprojectkotlin.di.component.ActivityComponent
import com.uttampanchasara.baseprojectkotlin.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_video_playback.*
import javax.inject.Inject

class VideoPlaybackActivity : BaseActivity(), VideoPlaybackView, View.OnClickListener {

    companion object {
        const val DATA = "data"
    }

    @Inject
    lateinit var mPresenter: VideoPlaybackPresenter

    override fun getLayout(): Int = R.layout.activity_video_playback

    override fun injectComponents(mActivityComponent: ActivityComponent) {
        mActivityComponent.inject(this)
        mPresenter.onAttachView(this)
    }

    private lateinit var player: SimpleExoPlayer
    private lateinit var mediaDataSourceFactory: DataSource.Factory

    private var trackSelector: DefaultTrackSelector? = null
    private val videoTrackSelectionFactory = AdaptiveTrackSelection.Factory()

    private var mIsShowingSystemUi = false
    private var mIsFullScreen = false

    private var mVideoData: Data? = null

    override fun setUp(savedInstanceState: Bundle?) {
        setToolbar(toolbar, "", true)

        mVideoData = intent.getParcelableExtra(DATA) as Data
        mVideoData?.let { video ->
            mPresenter.observeVotes(video.id)
            txtName.text = video.title
            txtDate.text = video.import_datetime
            initializePlayer(video.images.original_mp4.mp4)
        }

        btnFullScreen.setOnClickListener(this)
        btnUpVote.setOnClickListener(this)
        btnDownVote.setOnClickListener(this)
    }

    private fun initializePlayer(url: String) {

        trackSelector = DefaultTrackSelector(videoTrackSelectionFactory)
        mediaDataSourceFactory = DefaultDataSourceFactory(this, Util.getUserAgent(this, "GiphySearch"))

        val mediaSource = ExtractorMediaSource.Factory(mediaDataSourceFactory)
                .createMediaSource(Uri.parse(url))

        player = ExoPlayerFactory.newSimpleInstance(this, trackSelector)

        player.prepare(mediaSource, false, false)
        player.playWhenReady = true

        playerView.setShutterBackgroundColor(Color.TRANSPARENT)
        playerView.player = player
        playerView.requestFocus()
    }

    private fun releasePlayer() {
        player.release()
        trackSelector = null
    }

    public override fun onPause() {
        super.onPause()
        releasePlayer()
    }

    public override fun onStop() {
        super.onStop()
        releasePlayer()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnFullScreen -> {
                if (!mIsFullScreen) {
                    mIsFullScreen = true
                    requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                } else {
                    mIsFullScreen = false
                    requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                }
            }
            R.id.btnDownVote -> {
                mVideoData?.let {
                    mPresenter.updateVotes(it.id, txtUpVote.text.toString().toInt(), txtDownVote.text.toString().toInt() + 1)
                }
            }
            R.id.btnUpVote -> {
                mVideoData?.let {
                    mPresenter.updateVotes(it.id, txtUpVote.text.toString().toInt() + 1, txtDownVote.text.toString().toInt())
                }
            }
        }
    }

    override fun onVotesUpdated() {
        mVideoData?.let {
            mPresenter.observeVotes(it.id)
        }
    }

    override fun onVotesAvailable(it: List<FavoriteVideos>) {
        txtUpVote.text = "0"
        txtDownVote.text = "0"
        if (it.isNotEmpty()) {
            txtUpVote.text = it[0].upvote.toString()
            txtDownVote.text = it[0].downVote.toString()
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        when (newConfig.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> {
                val lp = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT)
                root.layoutParams = lp
            }
            Configuration.ORIENTATION_PORTRAIT -> {
                val lp = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, resources.getDimension(R.dimen._200sdp).toInt())
                root.layoutParams = lp
            }
        }

        handleSystemUIVisibility()
    }

    private fun handleSystemUIVisibility() {
        if (mIsShowingSystemUi) {
            mIsShowingSystemUi = false
            mIsFullScreen = false
            enableFullScreen(false)
            btnFullScreen.setImageResource(R.drawable.ic_video_small_screen)
        } else {
            mIsShowingSystemUi = true
            mIsFullScreen = true
            enableFullScreen(true)
            btnFullScreen.setImageResource(R.drawable.ic_video_full_screen)
        }
    }
}