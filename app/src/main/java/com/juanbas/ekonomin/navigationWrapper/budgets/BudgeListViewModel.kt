package com.juanbas.ekonomin.navigationWrapper.budgets

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.juanbas.ekonomin.dataBase.Entities.BudgetEntity
import com.juanbas.ekonomin.dataBase.Repositories.BudgetRepository

open class BudgeListViewModel(application: Application): AndroidViewModel(application) {

        private val budgetRepository = BudgetRepository(application)

        fun updateBudget(budget: BudgetEntity) {
            budgetRepository.updateBudget(budget)
        }

        fun deleteBudget(budget: BudgetEntity) {
            budgetRepository.deleteBudget(budget)
        }

        fun getBudgetByOwnerId(id: String) = budgetRepository.getBudgetByOwnerId(id)

        fun getAllBudgetsByOwnerId(id: String) = budgetRepository.getAllBudgetsByOwnerId(id)

        fun getAllBudgets() = budgetRepository.getAllBudgets()

}