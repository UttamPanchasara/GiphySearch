package com.uttampanchasara.baseprojectkotlin.ui.videoplayback

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.os.Handler
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
import com.uttampanchasara.baseprojectkotlin.data.repository.videos.Data
import com.uttampanchasara.baseprojectkotlin.di.component.ActivityComponent
import com.uttampanchasara.baseprojectkotlin.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_video_playback.*

class VideoPlaybackActivity : BaseActivity() {

    companion object {
        const val DATA = "data"
    }

    override fun getLayout(): Int = R.layout.activity_video_playback

    override fun injectComponents(mActivityComponent: ActivityComponent) {
        mActivityComponent.inject(this)
    }

    private lateinit var player: SimpleExoPlayer
    private lateinit var mediaDataSourceFactory: DataSource.Factory

    private var trackSelector: DefaultTrackSelector? = null
    private val videoTrackSelectionFactory = AdaptiveTrackSelection.Factory()

    private var mIsShowingSystemUi = false
    private var mIsFullScreen = false

    override fun setUp(savedInstanceState: Bundle?) {
        setToolbar(toolbar, "", true)

        val video = intent.getParcelableExtra(DATA) as Data
        initializePlayer(video.images.original_mp4.mp4)

        btnFullScreen.setOnClickListener {
            if (!mIsFullScreen) {
                mIsFullScreen = true
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            } else {
                mIsFullScreen = false
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            }
        }
    }

    private fun initializePlayer(url: String) {

        trackSelector = DefaultTrackSelector(videoTrackSelectionFactory)
        mediaDataSourceFactory = DefaultDataSourceFactory(this, Util.getUserAgent(this, "mediaPlayerSample"))

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

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        handleSystemUIVisibility()
        when (newConfig.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> {
                val lp = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT)
                root.layoutParams = lp
                Handler().postDelayed(Runnable {
                    if (requestedOrientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
                        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
                }, 5000)
            }
            Configuration.ORIENTATION_PORTRAIT -> {
                val lp1 = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, resources.getDimension(R.dimen._200sdp).toInt())
                root.layoutParams = lp1
                Handler().postDelayed(Runnable {
                    if (requestedOrientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
                }, 5000)
            }
        }
    }

    private fun handleSystemUIVisibility() {
        if (mIsShowingSystemUi) {
            mIsShowingSystemUi = false
            mIsFullScreen = true
            showSystemUI()
            btnFullScreen.setImageResource(R.drawable.ic_video_small_screen)
        } else {
            mIsShowingSystemUi = true
            mIsFullScreen = false
            hideSystemUI()
            btnFullScreen.setImageResource(R.drawable.ic_video_full_screen)
        }
    }

    // This snippet hides the system bars.
    private fun hideSystemUI() {
        // Set the IMMERSIVE report_flag.
        // Set the content to appear under the system bars so that the content
        // doesn't resize when the system bars hide and show.
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or // hide nav bar

                View.SYSTEM_UI_FLAG_FULLSCREEN or // hide status bar

                View.SYSTEM_UI_FLAG_IMMERSIVE
    }

    // This snippet shows the system bars. It does this by removing all the flags
    // except for the ones that make the content appear under the system bars.
    private fun showSystemUI() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
    }
}