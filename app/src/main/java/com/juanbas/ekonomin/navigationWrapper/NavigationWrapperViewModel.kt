package com.juanbas.ekonomin.navigationWrapper

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.AndroidViewModel
import com.juanbas.ekonomin.dataBase.entities.UserEntity
import com.juanbas.ekonomin.dataBase.repositories.UserRepository
/** Handles logic not associated with UI from */
class NavigationWrapperViewModel(application: Application) : AndroidViewModel(application) {
    private val userRepository = UserRepository(application)
    companion object {
        private const val NAVIGATION_WRAPPER_TAG = "NavigationWrapperTag"
        private const val USER_ID = "userId"
        private const val USER_NAME = "userName"
    }

    fun updateUserSessionState(userInfo: Bundle, isLogged: Boolean){
        val user = UserEntity(userInfo.getString(USER_ID),userInfo.getString(USER_NAME),isLogged)
        userRepository.updateUser(user)
    }
}
