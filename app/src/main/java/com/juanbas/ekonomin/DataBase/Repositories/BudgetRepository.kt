package com.juanbas.ekonomin.DataBase.Repositories

import android.app.Application
import androidx.lifecycle.LiveData
import com.juanbas.ekonomin.DataBase.Entities.BudgetEntity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class BudgetRepository(application: Application) : Repository(application) {

    private val budgetDao = ekonominDataBase?.budgetDao()
    private var allBudgets = budgetDao?.getAllBudgets()

    fun insertBudget(budget: BudgetEntity): Job {
        return GlobalScope.launch {
            budgetDao?.insertBudget(budget)
        }
    }

    fun updateBudget(budget: BudgetEntity): Job {
        return GlobalScope.launch {
            budgetDao?.updateBudget(budget)
        }
    }

    fun getBudgetByOwnerId(id: String) = budgetDao?.getBudgetByOwnerId(id)

    fun deleteBudget(budget: BudgetEntity): Job {
        return GlobalScope.launch {
            budgetDao?.deleteBudget(budget)
        }
    }

    fun deleteAllBudgets(budget: BudgetEntity): Job {
        return GlobalScope.launch {
            budgetDao?.deleteAllBudgets()
        }
    }

    fun getAllBudgets(): LiveData<List<BudgetEntity>>? {
        return allBudgets
    }

}