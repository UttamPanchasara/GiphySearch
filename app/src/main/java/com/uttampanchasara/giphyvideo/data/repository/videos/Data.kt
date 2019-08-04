package com.uttampanchasara.giphyvideo.data.repository.videos

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Data(
        val id: String,
        val images: Images,
        val title: String,
        val import_datetime: String
) : Parcelable