package com.juanbas.ekonomin.dataBase.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import com.juanbas.ekonomin.dataBase.entities.BudgetEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/** Used to retrieve and insert [BudgetEntity]s from database or any other external storage. */
class BudgetRepository(application: Application) : Repository(application) {
    companion object{
        lateinit var budgetEntity: BudgetEntity
    }
    private val budgetDao = ekonominDataBase?.budgetDao()

    fun insertBudget(budget: BudgetEntity): Job {
        return CoroutineScope(IO).launch {
            budgetDao?.insertBudget(budget)
        }
    }

    fun updateBudget(budget: BudgetEntity): Job {
        return CoroutineScope(IO).launch{
            budgetDao?.updateBudget(budget)
        }
    }

    fun getBudgetByOwnerId(id: String) = budgetDao?.getBudgetByOwnerId(id)

    fun deleteBudget(budget: BudgetEntity): Job {
        return CoroutineScope(IO).launch{
            budgetDao?.deleteBudget(budget)
        }
    }

    fun getAllBudgetsByOwnerId(id:String): LiveData<List<BudgetEntity>>? = budgetDao?.getAllBudgetsByOwnerId(id)

    fun getAllBudgets(): LiveData<List<BudgetEntity>>? = budgetDao?.getAllBudgets()

}