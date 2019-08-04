package com.uttampanchasara.giphyvideo

import android.app.Application
import com.uttampanchasara.giphyvideo.data.DataManager
import com.uttampanchasara.giphyvideo.di.component.DaggerAppComponent
import com.uttampanchasara.giphyvideo.di.module.AppModule
import com.uttampanchasara.giphyvideo.utils.PrefUtils
import javax.inject.Inject
import com.androidnetworking.AndroidNetworking


class AppController : Application() {

    companion object {
        lateinit var mAppController: AppController
    }

    @set:Inject
    internal var mDataManager: DataManager? = null

    lateinit var mAppComponent: DaggerAppComponent

    override fun onCreate() {
        super.onCreate()
        PrefUtils.init(this)
        mAppController = this

        AndroidNetworking.initialize(applicationContext)

        mAppComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build() as DaggerAppComponent

        mAppComponent.inject(this)
    }

    /*override fun attachBaseContext(context: Context) {
        super.attachBaseContext(context)
        MultiDex.install(this)
    }*/
}