package com.juanbas.ekonomin.dataBase.Entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "income_table",
    foreignKeys = [
        ForeignKey(
            entity = BudgetEntity::class,
            parentColumns = arrayOf("budgetId"),
            childColumns = arrayOf("budgetId")
        )],
    indices = [Index(value = ["incomeId"])]
)
data class IncomeEntity(
    @PrimaryKey(autoGenerate = true)
    val incomeId: Int?,
    val budgetId: Int?,
    val incomeOwner: String,
    val incomeValue: Double,
    val incomeSource:String)