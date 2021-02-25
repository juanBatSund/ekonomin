package com.juanbas.ekonomin.navigationWrapper.budgets.expenses

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.juanbas.ekonomin.dataBase.Entities.ExpenseEntity
import com.juanbas.ekonomin.dataBase.Repositories.ExpenseRepository
/**
 * Handles data change on ExpenseRepository. This can also be used to change data in the
 * layout in the future.
 */
class ExpenseViewModel(application: Application): AndroidViewModel(application) {
    val expenseRepository by lazy { ExpenseRepository(application) }

    fun insertExpense(expense: ExpenseEntity) = expenseRepository.insertExpense(expense)

    fun getExpensesByBudgetId(id: Int?) = expenseRepository.getExpensesByBudgetId(id)

}