package com.example.retrofit.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object UserConfig {
    private const val BASE_URL = "https://642eadfa2b883abc64141693.mockapi.io/login/"
    private val builder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        val retrofit = builder.build()
    val apiService : UserApi = retrofit.create(UserApi::class.java)
}