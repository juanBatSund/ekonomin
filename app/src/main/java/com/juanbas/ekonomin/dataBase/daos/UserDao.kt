package com.juanbas.ekonomin.dataBase.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.juanbas.ekonomin.dataBase.entities.UserEntity

/** Dao used to retrieve the [UserEntity] instances from the database. */
@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: UserEntity)
    @Update()
    fun updateUser(user: UserEntity)

    @Delete()
    fun deleteUser(user: UserEntity)

    @Query("DELETE FROM users_table")
    fun deleteAllUsers()

    @Query("SELECT * FROM users_table WHERE userId=:id")
    suspend fun getUserById(id: String): UserEntity

    @Query("SELECT * FROM users_table ORDER BY userId DESC")
    fun getAllUsers(): LiveData<List<UserEntity>>

    @Query("SELECT isLoggedIn FROM users_table WHERE userId=:id")
    fun isUserLoggedIn(id: String): LiveData<Boolean>

}