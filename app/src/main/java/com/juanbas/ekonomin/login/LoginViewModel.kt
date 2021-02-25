package com.juanbas.ekonomin.login

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.juanbas.ekonomin.dataBase.Entities.UserEntity
import com.juanbas.ekonomin.dataBase.Repositories.UserRepository

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val userRepository = UserRepository(application)

    companion object {
        private const val LOGIN_VIEW_MODEL_TAG = "NavigationWrapperTag"
    }

    fun registerUser(userName: String, userId: String) {
        //Create entity instance for user and set it on memory to be used globally
        val currentSessionUser = UserEntity(userId, userName)
        // Insert or update the database
        var userRetrieved = userRepository.getUserById(userId)
        if (userRetrieved == null) {
            userRepository.insertUser(currentSessionUser)
            Log.d(LOGIN_VIEW_MODEL_TAG, "User does not exist inserting")
        } else {
            userRepository.updateUser(currentSessionUser)
            Log.d(LOGIN_VIEW_MODEL_TAG, "User already exists updating")
        }
    }
}