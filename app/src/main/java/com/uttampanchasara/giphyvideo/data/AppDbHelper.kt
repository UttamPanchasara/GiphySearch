package com.uttampanchasara.giphyvideo.data

import com.uttampanchasara.giphyvideo.data.repository.favvideos.FavoriteVideos
import com.uttampanchasara.giphyvideo.data.repository.favvideos.FavoriteVideos_
import io.objectbox.BoxStore
import io.objectbox.query.Query
import io.reactivex.Single
import javax.inject.Inject

/**
 *
 * @author <a href="https://github.com/UttamPanchasara">Uttam Panchasara</a>
 * @since 11/15/2018
 */
class AppDbHelper @Inject internal constructor(private val boxStore: BoxStore) : DbHelper {
    override fun getUpVote(videoId: String): Single<FavoriteVideos> {

        return Single.fromCallable {
            return@fromCallable boxStore.boxFor(FavoriteVideos::class.java).query().equal(FavoriteVideos_.videoId, videoId).build().findFirst()
        }
    }

    override fun observeVotes(videoId: String): Query<FavoriteVideos> {
        return boxStore.boxFor(FavoriteVideos::class.java).query().equal(FavoriteVideos_.videoId, videoId).build()
    }

    override fun updateVotes(id: Long, videoId: String, upVote: Int, downVote: Int): Single<Boolean> {
        return Single.fromCallable {
            val favoriteVideos = FavoriteVideos(id, videoId, upVote, downVote)
            boxStore.boxFor(FavoriteVideos::class.java).put(favoriteVideos)
            return@fromCallable true
        }
    }
}