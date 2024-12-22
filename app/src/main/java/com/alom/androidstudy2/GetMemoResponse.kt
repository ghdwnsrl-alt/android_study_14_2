package com.alom.androidstudy2

import com.google.gson.annotations.SerializedName

data class GetMemoResponse(
    @SerializedName("result") val result: Int,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: List<Memo>
)