package com.example.exampleretrofitcomposeapp.data.remote

import com.example.exampleretrofitcomposeapp.data.models.SerialCharacter
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterApi {


    // https://rickandmortyapi.com/api/character/2
    // response jest pojemnikiem na otrzymane dane
    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id: Int): Response<SerialCharacter>
}

object RemoteSource{
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/api/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val api = retrofit.create(CharacterApi::class.java)
}