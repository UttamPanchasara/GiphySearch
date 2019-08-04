package com.uttampanchasara.giphyvideo.di.module

import android.content.Context
import android.support.v7.app.AppCompatActivity
import com.uttampanchasara.giphyvideo.data.DataManager
import com.uttampanchasara.giphyvideo.di.ActivityContext
import com.uttampanchasara.giphyvideo.di.PerActivity
import com.uttampanchasara.giphyvideo.ui.dashboard.DashboardPresenter
import com.uttampanchasara.giphyvideo.ui.dashboard.home.HomePresenter
import com.uttampanchasara.giphyvideo.ui.splash.SplashPresenter
import com.uttampanchasara.giphyvideo.ui.videoplayback.VideoPlaybackPresenter
import com.uttampanchasara.giphyvideo.utils.rx.AppSchedulerProvider
import com.uttampanchasara.giphyvideo.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

/**
 *
 * @author <a href="https://github.com/UttamPanchasara">Uttam Panchasara</a>
 * @since 11/15/2018
 */
@Module
class ActivityModule constructor(val mActivity: AppCompatActivity) {

    @Provides
    @ActivityContext
    internal fun provideContext(): Context {
        return mActivity
    }

    @Provides
    internal fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

    @Provides
    internal fun provideSchedulerProvider(): SchedulerProvider {
        return AppSchedulerProvider()
    }

    @Provides
    @PerActivity
    internal fun provideDashboardPresenter(dataManager: DataManager, schedulerProvider: SchedulerProvider, compositeDisposable: CompositeDisposable):
            DashboardPresenter = DashboardPresenter(dataManager, schedulerProvider, compositeDisposable)

    @Provides
    @PerActivity
    internal fun provideSplashPresenter(dataManager: DataManager, schedulerProvider: SchedulerProvider, compositeDisposable: CompositeDisposable):
            SplashPresenter = SplashPresenter(dataManager, schedulerProvider, compositeDisposable)

    @Provides
    @PerActivity
    internal fun provideHomePresenter(dataManager: DataManager, schedulerProvider: SchedulerProvider, compositeDisposable: CompositeDisposable):
            HomePresenter = HomePresenter(dataManager, schedulerProvider, compositeDisposable)

    @Provides
    @PerActivity
    internal fun provideVideoPlaybackPresenter(dataManager: DataManager, schedulerProvider: SchedulerProvider, compositeDisposable: CompositeDisposable):
            VideoPlaybackPresenter = VideoPlaybackPresenter(dataManager, schedulerProvider, compositeDisposable)

}