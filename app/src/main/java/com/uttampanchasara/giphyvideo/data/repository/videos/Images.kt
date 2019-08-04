package com.uttampanchasara.giphyvideo.data.repository.videos

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Images(
        @SerializedName("480w_still")
        val w_still: WStill,
        val original_mp4: OriginalMp4
) : Parcelable