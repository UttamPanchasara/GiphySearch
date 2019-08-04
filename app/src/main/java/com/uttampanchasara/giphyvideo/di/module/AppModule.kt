package com.uttampanchasara.giphyvideo.di.module

import android.app.Application
import android.content.Context
import com.uttampanchasara.giphyvideo.AppConstants
import com.uttampanchasara.giphyvideo.BuildConfig
import com.uttampanchasara.giphyvideo.data.AppDataManager
import com.uttampanchasara.giphyvideo.data.AppDbHelper
import com.uttampanchasara.giphyvideo.data.DataManager
import com.uttampanchasara.giphyvideo.data.DbHelper
import com.uttampanchasara.giphyvideo.data.network.ApiHeader
import com.uttampanchasara.giphyvideo.data.network.ApiHelper
import com.uttampanchasara.giphyvideo.data.network.AppApiHelper
import com.uttampanchasara.giphyvideo.data.prefs.AppPreferencesHelper
import com.uttampanchasara.giphyvideo.data.prefs.PreferencesHelper
import com.uttampanchasara.giphyvideo.data.repository.favvideos.MyObjectBox
import com.uttampanchasara.giphyvideo.di.ApplicationContext
import com.uttampanchasara.giphyvideo.di.PreferenceInfo
import dagger.Module
import dagger.Provides
import io.objectbox.BoxStore
import javax.inject.Singleton

/**
 *
 * @author <a href="https://github.com/UttamPanchasara">Uttam Panchasara</a>
 * @since 11/15/2018
 */
@Module
class AppModule constructor(val mApplication: Application) {

    @Provides
    @ApplicationContext
    internal fun provideContext(): Context {
        return mApplication
    }

    @Provides
    internal fun provideApplication(): Application {
        return mApplication
    }

    @Provides
    @PreferenceInfo
    internal fun providePreferenceName(): String {
        return AppConstants.PREF_NAME
    }

    @Provides
    @Singleton
    internal fun providePreferencesHelper(appPreferencesHelper: AppPreferencesHelper): PreferencesHelper {
        return appPreferencesHelper
    }

    @Provides
    @Singleton
    internal fun provideApiHelper(appApiHelper: AppApiHelper): ApiHelper {
        return appApiHelper
    }

    @Provides
    @Singleton
    internal fun provideProtectedApiHeader(preferencesHelper: PreferencesHelper): ApiHeader.ProtectedApiHeader {
        return ApiHeader.ProtectedApiHeader(
                BuildConfig.API_KEY,
                "")
    }

    @Provides
    @Singleton
    internal fun provideAppDatabase(@ApplicationContext context: Context): BoxStore {
        return MyObjectBox.builder().androidContext(context).build()
    }

    @Provides
    @Singleton
    internal fun provideDataManager(appDataManager: AppDataManager): DataManager {
        return appDataManager
    }

    @Provides
    @Singleton
    internal fun provideDbHelper(appDbHelper: AppDbHelper): DbHelper {
        return appDbHelper
    }
}