package com.uttampanchasara.giphyvideo.data.repository.videos

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WStill(
    val height: String,
    val url: String,
    val width: String
) : Parcelable