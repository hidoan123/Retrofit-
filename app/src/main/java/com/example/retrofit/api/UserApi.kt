package com.example.retrofit.api

import com.example.retrofit.data.model.User
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserApi {
    @GET("/user")
    suspend fun getAllUser() : List<User>

    @POST("/user")
    suspend fun addUser(@Body note : User) : User

    @DELETE("/user/{id}")
    suspend fun deleteUser(@Path("id") id: Int) : User
}