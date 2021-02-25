package com.juanbas.ekonomin.navigationWrapper.budgets


import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.juanbas.ekonomin.dataBase.Repositories.BudgetRepository
import com.juanbas.ekonomin.dataBase.Repositories.IncomeRepository

/* Handles view changes on the activity_budget layout */
class BudgetViewModel(application: Application) : BudgeListViewModel(application) {

    val datePicked = MutableLiveData<String>()
    var totalIncome = MutableLiveData<Double?>(0.0)
    val budgetId by lazy { BudgetRepository.budgetEntity.budgetId }
    private val budgetRepository by lazy { BudgetRepository(application) }
    private val incomeRepository by lazy { IncomeRepository(application) }
    private var dueDay: Int? = 0
    private var dueMonth: Int? = 0
    private var dueYear: Int? = 0

    init {
        loadTotalIncome()
    }

    /** Sets the date for*/
    fun setDatePicked(year: Int?, month: Int?, day: Int?) {
        dueMonth = month
        dueYear = year
        dueDay = day
        datePicked.value = "$dueYear/$dueMonth/$dueDay"
    }

    private fun loadTotalIncome(){
        var totalValueFromDb =
            incomeRepository.getTotalIncomeByBudgetId(budgetId)
        totalIncome =
            totalValueFromDb?.let { Transformations.map(it) { total -> total } } as MutableLiveData<Double?>
    }

}