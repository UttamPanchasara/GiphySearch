package com.uttampanchasara.giphyvideo.di.component

import com.uttampanchasara.giphyvideo.di.PerActivity
import com.uttampanchasara.giphyvideo.di.module.ActivityModule
import com.uttampanchasara.giphyvideo.ui.dashboard.DashboardActivity
import com.uttampanchasara.giphyvideo.ui.dashboard.home.HomeController
import com.uttampanchasara.giphyvideo.ui.splash.SplashActivity
import com.uttampanchasara.giphyvideo.ui.videoplayback.VideoPlaybackActivity
import dagger.Component

/**
 *
 * @author <a href="https://github.com/UttamPanchasara">Uttam Panchasara</a>
 * @since 11/15/2018
 */
@PerActivity
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun inject(activity: SplashActivity)

    fun inject(activity: DashboardActivity)

    fun inject(homeController: HomeController)

    fun inject(videoPlaybackActivity: VideoPlaybackActivity)
}