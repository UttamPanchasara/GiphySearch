package com.uttampanchasara.giphyvideo.data.network

import com.uttampanchasara.giphyvideo.data.repository.videos.GiphyVideos

import io.reactivex.Single

interface ApiHelper {
    fun getGiphyVideos(query: String, offset: Int): Single<GiphyVideos>
}