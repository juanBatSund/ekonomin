package com.juanbas.ekonomin.navigationWrapper.budget

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.DatePicker
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.juanbas.ekonomin.R
import com.juanbas.ekonomin.dataBase.Entities.BudgetEntity
import com.juanbas.ekonomin.dataBase.Entities.UserEntity
import com.juanbas.ekonomin.dataBase.Repositories.BudgetRepository
import com.juanbas.ekonomin.dataBase.Repositories.Repository
import com.juanbas.ekonomin.dataBase.Repositories.UserRepository
import com.juanbas.ekonomin.databinding.ActivityBudgetBinding
import com.juanbas.ekonomin.navigationWrapper.budget.income.IncomeFragment
import kotlinx.android.synthetic.main.activity_budget.*
import java.util.*

/* Used to handle the listing and insertion of expenses and Incomes. */
class BudgetView : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    private lateinit var viewModel: BudgetViewModel
    private var userId: String? = null
    private lateinit var budgetEntity: BudgetEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(BudgetViewModel::class.java)
        DataBindingUtil.setContentView<ActivityBudgetBinding>(this, R.layout.activity_budget)
            .apply {
                this.setLifecycleOwner(this@BudgetView)
                this.viewmodel = viewModel
            }

        income_expense_pager.adapter = IncomeExpenseAdapter(this)

        TabLayoutMediator(income_expense_bar, income_expense_pager){
                tab, position ->
            when(position){
                0 -> tab.text = "Income"
                1 -> tab.text = "Expense"
            }

        }.attach()
        budgetEntity = BudgetRepository.budgetEntity
        userId = BudgetRepository.budgetEntity.userId
        viewModel.setDatePicked(budgetEntity.dueYear,budgetEntity.dueMonth, budgetEntity.dueDay)


    }

    fun checkDateTextEdit(unused: View) {
        val calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(
            this,
            R.style.CustomDatePickerDialog,
            this,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.setTitle(getString(R.string.date_picker_title))
        datePickerDialog.show()

    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        viewModel.setDatePicked(year, month.plus(1), dayOfMonth)
        BudgetRepository.budgetEntity.dueYear=year
        BudgetRepository.budgetEntity.dueMonth=month.plus(1)
        BudgetRepository.budgetEntity.dueDay=dayOfMonth
    }

    fun onSaveButtonClicked(v: View) {
        viewModel.updateBudget(BudgetRepository.budgetEntity)
        finish()
    }

    fun onCancelButtonClicked(unsuded: View) {
        finish()
    }


}
