package com.juanbas.ekonomin.navigationWrapper.budget.income

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.juanbas.ekonomin.dataBase.Repositories.IncomeRepository

class IncomeViewModel(application: Application) : AndroidViewModel(application)  {
    val incomeRepository by lazy { IncomeRepository(application)  }
    fun getAllIncomes() = incomeRepository.getAllIncomes()
    fun getAllIncomeByBudgetId(id:Int?) = incomeRepository.getAllIncomeByBudgetId(id)

}
