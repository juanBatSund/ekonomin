package com.juanbas.ekonomin.dataBase.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "budget_table")
data class BudgetEntity(@PrimaryKey(autoGenerate = true)
                            val budgetId: Int?,
                            val ownerId: String?,
                            val dueMonth: Int?,
                            val dueYear: Int?)
