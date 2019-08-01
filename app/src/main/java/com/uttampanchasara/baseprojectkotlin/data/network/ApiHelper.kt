package com.uttampanchasara.baseprojectkotlin.data.network

import com.uttampanchasara.baseprojectkotlin.data.repository.videos.GiphyVideos

import io.reactivex.Single

interface ApiHelper {
    fun getGiphyVideos(query: String, offset: Int): Single<GiphyVideos>
}