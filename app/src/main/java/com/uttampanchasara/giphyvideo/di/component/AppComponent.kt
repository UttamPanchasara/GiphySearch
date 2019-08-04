package com.uttampanchasara.giphyvideo.di.component

import android.app.Application
import android.content.Context
import com.uttampanchasara.giphyvideo.AppController
import com.uttampanchasara.giphyvideo.data.DataManager
import com.uttampanchasara.giphyvideo.di.ApplicationContext
import com.uttampanchasara.giphyvideo.di.module.AppModule
import dagger.Component
import javax.inject.Singleton

/**
 *
 * @author <a href="https://github.com/UttamPanchasara">Uttam Panchasara</a>
 * @since 11/15/2018
 */
@Singleton
@Component(modules = [(AppModule::class)])
interface AppComponent {

    @ApplicationContext
    fun context(): Context

    fun application(): Application

    fun getDataManager(): DataManager

    fun inject(app: AppController)

}