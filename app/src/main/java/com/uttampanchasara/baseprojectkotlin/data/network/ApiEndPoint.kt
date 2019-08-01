package com.uttampanchasara.baseprojectkotlin.data.network

import com.uttampanchasara.baseprojectkotlin.BuildConfig

object ApiEndPoint {
    private const val BASE_URL = BuildConfig.BASE_URL
    const val GET_SEARCH_VIDEO = BASE_URL + "search"

    internal interface KEYS {
        companion object {
            val API_KEY = "api_key"
            val QUERY = "q"
            val LIMIT = "limit"
            val OFFSET = "offset"
            val RATING = "rating"
            val LANG = "lang"
        }
    }
}