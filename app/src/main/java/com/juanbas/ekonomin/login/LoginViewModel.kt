package com.juanbas.ekonomin.login

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.core.os.bundleOf
import androidx.lifecycle.AndroidViewModel
import com.juanbas.ekonomin.dataBase.entities.UserEntity
import com.juanbas.ekonomin.dataBase.repositories.UserRepository

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val userRepository = UserRepository(application)

    companion object {
        const val USER_ID = "userId"
        const val USER_NAME = "userName"
        const val LOGIN_ACTIVITY_TAG = "logingActivityTag"
    }
    private lateinit var _userInfo: Bundle
    var userInfo: Bundle
        get() = _userInfo
        set(value) {
            _userInfo = value
        }


    fun registerUser(userName: String, userId: String, isLoggedIn: Boolean) {
        userInfo = bundleOf(Pair(USER_ID, userId), Pair(USER_NAME, userName))
        val currentSessionUser = UserEntity(userId, userName, isLoggedIn)
        // Insert or update the database
        var userRetrieved = userRepository.getUserById(userId)
        if (userRetrieved == null) {
            userRepository.insertUser(currentSessionUser)
            Log.d(LOGIN_ACTIVITY_TAG, "User does not exist inserting")
        } else {
            userRepository.updateUser(currentSessionUser)
            Log.d(LOGIN_ACTIVITY_TAG, "User already exists updating")
        }
    }
}