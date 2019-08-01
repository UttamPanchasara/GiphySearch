package com.uttampanchasara.baseprojectkotlin.data.repository.videos

import com.google.gson.annotations.SerializedName

data class Images(
        @SerializedName("480w_still")
        val w_still: WStill,
        val original_mp4: OriginalMp4
)