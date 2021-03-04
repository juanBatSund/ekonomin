package com.juanbas.ekonomin.dataBase.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import com.juanbas.ekonomin.dataBase.entities.UserEntity
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO


class UserRepository(application: Application) : Repository(application) {

    private  val userDao = ekonominDataBase?.userDao()

    fun insertUser(user: UserEntity): Job {
        return CoroutineScope(IO).launch{
            userDao?.insertUser(user)
        }
    }

    fun updateUser(user: UserEntity): Job {
        return CoroutineScope(IO).launch {
            userDao?.updateUser(user)
        }
    }

    fun deleteUser(user: UserEntity): Job {
        return CoroutineScope(IO).launch {
            userDao?.deleteUser(user)
        }
    }

    fun deleteAllUsers(): Job {
        return CoroutineScope(IO).launch {
            userDao?.deleteAllUsers()
        }
    }

    fun getAllUsers() : LiveData<List<UserEntity>>? {
        return userDao?.getAllUsers()
    }

    fun getUserById(id: String): UserEntity? = runBlocking{
        userDao?.getUserById(id)
    }

    fun isUserLoggedIn(id: String): LiveData<Boolean>? = userDao?.isUserLoggedIn(id)

}