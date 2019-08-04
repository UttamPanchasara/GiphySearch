package com.uttampanchasara.giphyvideo.data

import com.uttampanchasara.giphyvideo.data.repository.favvideos.FavoriteVideos
import io.objectbox.query.Query
import io.reactivex.Single

/**
 *
 * @author <a href="https://github.com/UttamPanchasara">Uttam Panchasara</a>
 * @since 11/15/2018
 */
interface DbHelper {

    fun updateVotes(id: Long, videoId: String, upVote: Int, downVote: Int): Single<Boolean>

    fun observeVotes(videoId: String): Query<FavoriteVideos>
    fun getUpVote(videoId: String): Single<FavoriteVideos>
}