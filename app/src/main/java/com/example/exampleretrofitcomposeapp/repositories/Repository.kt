package com.example.exampleretrofitcomposeapp.repositories

import android.content.Context
import com.example.exampleretrofitcomposeapp.data.local.CharacterDb
import com.example.exampleretrofitcomposeapp.data.models.SerialCharacter
import com.example.exampleretrofitcomposeapp.data.remote.RemoteSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import retrofit2.Response

class Repository(context: Context) {
    private val dao = CharacterDb.getInstance(context).characterDao()
    private val api = RemoteSource.api

    suspend fun loadCharacter(id: Int): Response<SerialCharacter>{
        return api.getCharacter(id)
    }

    fun getCharacter(id: Int): Flow<SerialCharacter> {
        return dao.getCharacter(id)
    }

    suspend fun insertAll(list: List<SerialCharacter>) = withContext(Dispatchers.IO){
        dao.insert(list)
    }
}