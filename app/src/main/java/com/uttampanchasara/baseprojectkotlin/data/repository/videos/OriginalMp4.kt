package com.uttampanchasara.baseprojectkotlin.data.repository.videos

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OriginalMp4(
        val height: String,
        val mp4: String,
        val mp4_size: String,
        val width: String
) : Parcelable