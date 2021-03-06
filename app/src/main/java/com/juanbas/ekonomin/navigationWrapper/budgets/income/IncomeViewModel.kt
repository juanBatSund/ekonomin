package com.juanbas.ekonomin.navigationWrapper.budgets.income

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.juanbas.ekonomin.dataBase.entities.IncomeEntity
import com.juanbas.ekonomin.dataBase.repositories.IncomeRepository

class IncomeViewModel(application: Application) : AndroidViewModel(application)  {
    val incomeRepository by lazy { IncomeRepository(application)  }

    fun insertIncome(income: IncomeEntity) = incomeRepository.insertIncome(income)

    fun getAllIncomeByBudgetId(id:Int?) = incomeRepository.getAllIncomeByBudgetId(id)

}
