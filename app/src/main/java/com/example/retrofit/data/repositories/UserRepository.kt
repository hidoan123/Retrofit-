package com.example.retrofit.data.repositories

import com.example.retrofit.api.UserConfig
import com.example.retrofit.data.model.User

class UserRepository {

    suspend fun getUsersFromApi() = UserConfig.apiService.getAllUser()
    suspend fun addUserToServer(user : User) = UserConfig.apiService.addUser(user)
}