package com.juanbas.ekonomin.navigationWrapper.budgets

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.DatePicker
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.tabs.TabLayoutMediator
import com.juanbas.ekonomin.R
import com.juanbas.ekonomin.dataBase.Entities.BudgetEntity
import com.juanbas.ekonomin.dataBase.Repositories.BudgetRepository
import com.juanbas.ekonomin.dataBase.Repositories.IncomeRepository
import com.juanbas.ekonomin.databinding.ActivityBudgetBinding
import kotlinx.android.synthetic.main.activity_budget.*
import java.util.*

/* Used to handle the listing and insertion of expenses and Incomes. */
class BudgetView : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    private lateinit var viewModel: BudgetViewModel
    private var userId: String? = null
    private lateinit var budgetEntity: BudgetEntity
    private lateinit var incomeRepository: IncomeRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        budgetEntity = BudgetRepository.budgetEntity
        //viewModel = BudgetViewModel(application,budgetEntity.budgetId)
        viewModel = ViewModelProviders.of(this).get(BudgetViewModel::class.java)
        val binding =
            DataBindingUtil.setContentView<ActivityBudgetBinding>(this, R.layout.activity_budget)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this


        income_expense_pager.adapter = IncomeExpenseAdapter(this)
        TabLayoutMediator(income_expense_bar, income_expense_pager) { tab, position ->
            when (position) {
                0 -> tab.text = "Income"
                1 -> tab.text = "Expense"
            }
        }.attach()
        userId = BudgetRepository.budgetEntity.userId
        viewModel.setDatePicked(budgetEntity.dueYear, budgetEntity.dueMonth, budgetEntity.dueDay)
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
        BudgetRepository.budgetEntity.dueYear = year
        BudgetRepository.budgetEntity.dueMonth = month.plus(1)
        BudgetRepository.budgetEntity.dueDay = dayOfMonth
        viewModel.updateBudget(BudgetRepository.budgetEntity)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}