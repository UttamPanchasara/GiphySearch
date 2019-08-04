package com.uttampanchasara.giphyvideo.data.repository.favvideos

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

/**
 * Created by Uttam on 2019-08-03.
 */
@Entity
data class FavoriteVideos(@Id var id: Long,
                          var videoId: String,
                          var upvote: Int,
                          var downVote: Int)