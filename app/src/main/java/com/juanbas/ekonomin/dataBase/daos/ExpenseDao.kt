package com.juanbas.ekonomin.dataBase.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.juanbas.ekonomin.dataBase.entities.ExpenseEntity

/** Dao used to retrieve the [ExpenseEntity] instances from the database. */
@Dao
interface ExpenseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExpense(expenseEntity: ExpenseEntity)

    @Update
    fun updateExpense(expenseEntity: ExpenseEntity)

    @Delete()
    fun deleteIncome(expenseEntity: ExpenseEntity)

    @Query("SELECT * FROM expense_table WHERE budgetId=:id")
    fun getExpenseByBudgetId(id: Int?): LiveData<List<ExpenseEntity>>

    @Query("SELECT * FROM expense_table ORDER BY budgetId DESC")
    fun getAllIncomes(): LiveData<List<ExpenseEntity>>
}
