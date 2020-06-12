package com.juanbas.ekonomin.navigationWrapper.budget



import android.app.Application
import androidx.lifecycle.*
import com.juanbas.ekonomin.dataBase.Entities.BudgetEntity
import com.juanbas.ekonomin.dataBase.Repositories.BudgetRepository

/* Handles view changes on the activity_budget layout */
class BudgetViewModel(application: Application) : AndroidViewModel(application) {

    var datePicked = MutableLiveData<String>()
    val budgetRepository by lazy {BudgetRepository(application)}
    var dueDay: Int? = 0
    var dueMonth: Int? = 0
    var dueYear: Int? = 0

    fun setDatePicked(year: Int?, month: Int?, day: Int?){
        dueMonth = month
        dueYear = year
        dueDay = day
        datePicked.value = "$dueYear/$dueMonth/$dueDay"
    }

    fun insertBudget(budgetOwnerId: String?){
        val budgetToSave = BudgetEntity(null, budgetOwnerId, dueMonth, dueYear,dueDay)
        budgetRepository.insertBudget(budgetToSave)
    }

    fun updateBudget(budget: BudgetEntity) {
        budgetRepository.updateBudget(budget)
    }

    fun deleteBudget(budget: BudgetEntity) {
        budgetRepository.deleteBudget(budget)
    }

    fun getBudgetByOwnerId(id: String) = budgetRepository.getBudgetByOwnerId(id)
    fun getAllBudgetss() = budgetRepository.getAllBudgets()

}