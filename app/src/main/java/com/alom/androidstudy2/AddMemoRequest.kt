package com.alom.androidstudy2

import com.google.gson.annotations.SerializedName



data class AddMemoRequest(
    @SerializedName("p_title") val title: String,
    @SerializedName("p_price") val price: String,
    @SerializedName("p_time") val time: String
)