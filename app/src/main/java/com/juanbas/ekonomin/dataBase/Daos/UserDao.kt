package com.juanbas.ekonomin.dataBase.Daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.juanbas.ekonomin.dataBase.Entities.UserEntity
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
    fun getUserById(id: String): LiveData<UserEntity>

    @Query("SELECT * FROM users_table ORDER BY userId DESC")
    fun getAllUsers(): LiveData<List<UserEntity>>

}