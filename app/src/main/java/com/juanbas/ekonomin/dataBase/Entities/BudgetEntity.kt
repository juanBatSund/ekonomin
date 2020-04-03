package com.juanbas.ekonomin.dataBase.Entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "budget_table",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = arrayOf("userId"),
            childColumns = arrayOf("budgetId")
        )]
)
data class BudgetEntity(
    @PrimaryKey(autoGenerate = true)
    val budgetId: Int?,
    val userId: String?,
    val dueMonth: Int?,
    val dueYear: Int?
)
