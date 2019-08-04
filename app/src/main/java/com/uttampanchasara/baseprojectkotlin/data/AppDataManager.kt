package com.uttampanchasara.baseprojectkotlin.data

import android.content.Context
import com.uttampanchasara.baseprojectkotlin.data.network.ApiHeader
import com.uttampanchasara.baseprojectkotlin.data.network.ApiHelper
import com.uttampanchasara.baseprojectkotlin.data.prefs.PreferencesHelper
import com.uttampanchasara.baseprojectkotlin.data.repository.favvideos.FavoriteVideos
import com.uttampanchasara.baseprojectkotlin.data.repository.videos.Data
import com.uttampanchasara.baseprojectkotlin.data.repository.videos.GiphyVideos
import com.uttampanchasara.baseprojectkotlin.di.ApplicationContext
import io.objectbox.query.Query
import io.reactivex.Maybe
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

/**
 *
 * @author <a href="https://github.com/UttamPanchasara">Uttam Panchasara</a>
 * @since 11/15/2018
 */
@Singleton
class AppDataManager
@Inject internal constructor(@ApplicationContext val context: Context,
                             private val dbHelper: DbHelper,
                             private val preferencesHelper: PreferencesHelper,
                             private val apiHelper: ApiHelper) : DataManager {
    override fun getUpVote(videoId: String): Single<FavoriteVideos> {
        return dbHelper.getUpVote(videoId)
    }

    override fun observeVotes(videoId: String): Query<FavoriteVideos> {
        return dbHelper.observeVotes(videoId)
    }

    override fun updateVotes(id: Long, videoId: String, upVote: Int, downVote: Int): Single<Boolean> {
        return dbHelper.updateVotes(id, videoId, upVote, downVote)
    }

    override fun getGiphyVideos(query: String, offset: Int): Single<GiphyVideos> {
        return apiHelper.getGiphyVideos(query, offset)
    }

    override fun setAccessToken(accessToken: String?) {
        preferencesHelper.accessToken = accessToken
    }

    override fun getAccessToken(): String {
        return preferencesHelper.accessToken
    }
}