package com.alom.androidstudy2
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitInterface {
    @POST("/rpc/add_item4")
    suspend fun addMemo(
        @Body request:AddMemoRequest
    ): Response<AddMemoResponse>

    @GET("/rpc/get_item4")
    suspend fun getMemo(
    ):Response<GetMemoResponse>


}