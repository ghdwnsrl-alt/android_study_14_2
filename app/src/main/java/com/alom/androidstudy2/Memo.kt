package com.alom.androidstudy2

import com.google.gson.annotations.SerializedName

data class Memo(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("price") val price: String,
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("time") val time: String
)

