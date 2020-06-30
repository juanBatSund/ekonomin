package com.juanbas.ekonomin.dataBase.Entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.juanbas.ekonomin.dataBase.Entities.BudgetEntity

/** Entity representing each expense instance to be saved in the database. */
@Entity(
    tableName = "expense_table",
    foreignKeys = [
        ForeignKey(
            entity = BudgetEntity::class,
            parentColumns = arrayOf("budgetId"),
            childColumns = arrayOf("budgetId")
        )],
    indices = [Index(value = ["expenseId"])]
    )
data class ExpenseEntity(
    @PrimaryKey(autoGenerate = true)
    val expenseId: Int?,
    val budgetId: Int?,
    val expenseName: String,
    val expenseValue: Double,
    val expenseCategory: String
) {
}