package com.juanbas.ekonomin.dataBase.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

/** Entity representing each budget instance to be saved in the database. */
@Entity(
    tableName = "budget_table",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = arrayOf("userId"),
            childColumns = arrayOf("userId"),
            onDelete = ForeignKey.CASCADE
        )],
    indices = [Index(value = ["budgetId"])]
)
data class BudgetEntity(
    @PrimaryKey(autoGenerate = true)
    val budgetId: Int?,
    val userId: String?,
    var dueMonth: Int?,
    var dueYear: Int?,
    var dueDay: Int?
)
