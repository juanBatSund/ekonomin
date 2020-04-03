package com.juanbas.ekonomin.dataBase.Repositories

import android.app.Application
import androidx.lifecycle.LiveData
import com.juanbas.ekonomin.dataBase.Entities.UserEntity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.w3c.dom.Entity

class UserRepository(application: Application) : Repository(application) {

    private  val userDao = ekonominDataBase?.userDao()

    fun insertUser(user: UserEntity): Job {
        return GlobalScope.launch {
            userDao?.insertUser(user)
        }
    }

    fun updateUser(user: UserEntity): Job {
        return GlobalScope.launch {
            userDao?.updateUser(user)
        }
    }

    fun deleteUser(user: UserEntity): Job {
        return GlobalScope.launch {
            userDao?.deleteUser(user)
        }
    }

    fun deleteAllUsers(user: UserEntity): Job {
        return GlobalScope.launch {
            userDao?.deleteAllUsers()
        }
    }

    fun getAllUsers() : LiveData<List<UserEntity>>? {
        return userDao?.getAllUsers()
    }

    fun getUserById(id: String): LiveData<UserEntity>?{
        return userDao?.getUserById(id)
    }

    companion object{
        lateinit var sessionUser:UserEntity
    }

}