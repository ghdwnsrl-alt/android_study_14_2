package com.alom.androidstudy2

interface MemoRepository {
    suspend fun getMemos(): List<Memo>
    suspend fun addMemo(memo: Memo): AddMemoResponse
    suspend fun setMemo(memo: Memo)



}