package com.alom.androidstudy2



import android.content.SharedPreferences
import com.google.gson.Gson

class RepositoryImpl(
    private val sharedPreferences: SharedPreferences,
    private val apiService: RetrofitInterface
) : MemoRepository {

    private val gson = Gson()
    private val memoKey = "memo_list"


    override suspend fun setMemo(memo: Memo) {

        val memoJson = sharedPreferences.getString(memoKey, null)

        val memoList = if (memoJson.isNullOrEmpty()) {
            mutableListOf()
        } else {
            gson.fromJson(memoJson, Array<Memo>::class.java).toMutableList()
        }

        memoList.add(memo)

        val updatedJson = gson.toJson(memoList)

        sharedPreferences.edit().putString(memoKey, updatedJson).apply()
    }

    override suspend fun getMemos(): List<Memo> {
        val memoJson = sharedPreferences.getString(memoKey, null)
        return if (memoJson.isNullOrEmpty()) {
            emptyList()
        } else {
            gson.fromJson(memoJson, Array<Memo>::class.java).toList()
        }
    }



    override suspend fun addMemo(memo: Memo): AddMemoResponse {
        setMemo(memo)

        val request = AddMemoRequest(memo.title, memo.price, memo.time)
        val response = apiService.addMemo(request)

        return if (response.isSuccessful) {

            response.body() ?: AddMemoResponse(result = 200)
        } else {

            AddMemoResponse(result = response.code())
        }
    }
}
