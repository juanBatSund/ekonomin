package com.juanbas.ekonomin.dataBase.Repositories

import android.app.Application
import androidx.lifecycle.LiveData
import com.juanbas.ekonomin.dataBase.EkonominDataBase_Impl
import com.juanbas.ekonomin.dataBase.Entities.IncomeEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/** Used to retrieve and insert [IncomeEntity]s from database or any other external storage. */
class IncomeRepository(application: Application): Repository(application) {

    private val incomeDao = ekonominDataBase?.incomeDao()

    fun insertIncome(income: IncomeEntity): Job {
        return CoroutineScope(IO).launch{
            incomeDao?.insertIncome(income)
        }
    }

    fun updateIncome(income: IncomeEntity): Job {
        return CoroutineScope(IO).launch {
            incomeDao?.updateIncome(income)
        }
    }

    fun getAllIncomeByBudgetId(id: Int?): LiveData<List<IncomeEntity>>? = incomeDao?.getIncomeByBudgetId(id)

    fun getAllIncomes(): LiveData<List<IncomeEntity>>? = incomeDao?.getAllIncomes()

}