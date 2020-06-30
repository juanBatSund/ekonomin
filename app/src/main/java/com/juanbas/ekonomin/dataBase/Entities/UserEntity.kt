package com.juanbas.ekonomin.dataBase.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/** Entity representing each user instance to be saved in the database. */
@Entity(
    tableName = "users_table",
    indices = [Index(value= ["userId"], unique = true)]
)
data class UserEntity(
    @PrimaryKey(autoGenerate = false)
    val userId:String,
    val userName: String?) {
}

