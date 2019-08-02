package com.uttampanchasara.baseprojectkotlin.di.component

import com.uttampanchasara.baseprojectkotlin.di.PerActivity
import com.uttampanchasara.baseprojectkotlin.di.module.ActivityModule
import com.uttampanchasara.baseprojectkotlin.ui.dashboard.DashboardActivity
import com.uttampanchasara.baseprojectkotlin.ui.dashboard.home.HomeController
import com.uttampanchasara.baseprojectkotlin.ui.splash.SplashActivity
import com.uttampanchasara.baseprojectkotlin.ui.videoplayback.VideoPlaybackActivity
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