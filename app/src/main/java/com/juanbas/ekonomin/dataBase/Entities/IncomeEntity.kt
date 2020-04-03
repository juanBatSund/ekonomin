package com.juanbas.ekonomin.dataBase.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "income_table")
data class IncomeEntity(
    @PrimaryKey(autoGenerate = true)
    val budgetId: Int?,
    val incomeOwner: String,
    val incomeValue: Double,
    val incomeSource:String
    )