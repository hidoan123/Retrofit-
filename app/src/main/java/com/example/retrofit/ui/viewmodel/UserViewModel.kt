package com.example.retrofit.ui.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.retrofit.data.model.User
import com.example.retrofit.data.repositories.UserRepository
import com.example.retrofit.utils.Resource
import kotlinx.coroutines.Dispatchers

class UserViewModel : ViewModel() {
    private val userRepository = UserRepository()
    //get all user
   fun getUserFromAPi() = liveData(Dispatchers.IO) {
       emit(Resource.loading(null))
        try {
            emit(Resource.success(userRepository.getUsersFromApi()))
        }catch (ex :Exception){
            emit(Resource.error(null, ex.message ?: "error"))
        }

   }
    //add user
    fun addUserFromApi(user : User) = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            emit(Resource.success(userRepository.addUserToServer(user)))
        }catch (ex : Exception){
            emit(Resource.error(null, ex.message ?: "error"))
        }
    }

    class UserViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return UserViewModel() as T
            }

            throw IllegalArgumentException("Unable construct viewModel")
        }

    }
}