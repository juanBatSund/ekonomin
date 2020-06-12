package com.juanbas.ekonomin.dataBase.Repositories

import android.app.Application
import androidx.lifecycle.LiveData
import com.juanbas.ekonomin.dataBase.EkonominDataBase_Impl
import com.juanbas.ekonomin.dataBase.Entities.IncomeEntity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class IncomeRepository(application: Application): Repository(application) {

    private val incomeDao = ekonominDataBase?.incomeDao()


    fun insertIncome(income: IncomeEntity): Job {
        return GlobalScope.launch {
            incomeDao?.insertIncome(income)
        }
    }

    fun updateIncome(income: IncomeEntity): Job {
        return GlobalScope.launch {
            incomeDao?.updateIncome(income)
        }
    }

    fun getAllIncomeByBudgetId(id: Int?): LiveData<List<IncomeEntity>>? {
        return incomeDao?.getIncomeByBudgetId(id)
    }

    fun getAllIncomes(): LiveData<List<IncomeEntity>>? {
        return incomeDao?.getAllIncomes()
    }

}