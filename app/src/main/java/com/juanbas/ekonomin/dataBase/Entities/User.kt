package com.juanbas.ekonomin.dataBase.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "users",
    indices = [Index(value= ["userId"], unique = true)]
)
data class User(
    @PrimaryKey(autoGenerate = false)
    val userId:String?,
    val userName: String?) {
}

