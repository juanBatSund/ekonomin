package com.juanbas.ekonomin.DataBase.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "budget_table")
data class BudgetEntity(@PrimaryKey(autoGenerate = true)
                            val budgetId: Int?,
                            val ownerId: String?,
                            val budgetName: String?)
