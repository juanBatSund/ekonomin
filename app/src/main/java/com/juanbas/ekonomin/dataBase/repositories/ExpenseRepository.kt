package com.juanbas.ekonomin.dataBase.repositories

import android.app.Application
import com.juanbas.ekonomin.dataBase.entities.ExpenseEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/** Used to retrieve and insert [ExpenseEntity]s from database or any other external storage. */
class ExpenseRepository(application: Application): Repository(application) {

    private val expenseDao = ekonominDataBase?.expenseDao()

    fun insertExpense(expense: ExpenseEntity): Job {
        return CoroutineScope(IO).launch{
            expenseDao?.insertExpense(expense)
        }
    }

    fun updateExpense(expense: ExpenseEntity): Job {
        return CoroutineScope(IO).launch{
            expenseDao?.updateExpense(expense)
        }
    }

    fun getExpensesByBudgetId(id: Int?) = expenseDao?.getExpenseByBudgetId(id)

    fun getAllExpenses() = expenseDao?.getAllIncomes()

}