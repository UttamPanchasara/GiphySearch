package com.uttampanchasara.baseprojectkotlin.data.network

import com.rx2androidnetworking.Rx2AndroidNetworking
import com.uttampanchasara.baseprojectkotlin.data.repository.videos.GiphyVideos

import javax.inject.Inject
import javax.inject.Singleton

import io.reactivex.Single

@Singleton
class AppApiHelper @Inject
constructor(private val mApiHeader: ApiHeader) : ApiHelper {

    override fun getGiphyVideos(query: String, offset: Int): Single<GiphyVideos> {
        return Rx2AndroidNetworking.get(ApiEndPoint.GET_SEARCH_VIDEO)
                .addQueryParameter(ApiEndPoint.KEYS.API_KEY, mApiHeader.protectedApiHeader.apiKey)
                .addQueryParameter(ApiEndPoint.KEYS.QUERY, query)
                .addQueryParameter(ApiEndPoint.KEYS.LIMIT, "25")
                .addQueryParameter(ApiEndPoint.KEYS.OFFSET, offset.toString())
                .addQueryParameter(ApiEndPoint.KEYS.RATING, "g")
                .addQueryParameter(ApiEndPoint.KEYS.LANG, "en")
                .build()
                .getObjectSingle(GiphyVideos::class.java)
    }
}