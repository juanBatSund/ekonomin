package com.juanbas.ekonomin.navigationWrapper.budget

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.DatePicker
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.juanbas.ekonomin.R
import com.juanbas.ekonomin.databinding.ActivityBudgetBinding
import java.util.*

/* Used to add and edit budgets */
class BudgetView : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    lateinit var viewModel: BudgetViewModel
    var userId: String? = String()
    private val ADD_BUDGET_TAG="BudgetAddView"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userId = intent.getStringExtra("userId")
        Log.d(ADD_BUDGET_TAG,"User id received from intent: $userId")
        viewModel = ViewModelProviders.of(this).get(BudgetViewModel::class.java)
        DataBindingUtil.setContentView<ActivityBudgetBinding>(this, R.layout.activity_budget)
            .apply {
                this.setLifecycleOwner(this@BudgetView)
                this.viewmodel = viewModel
            }

    }

    fun checkDateTextEdit(unused: View) {
        val calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(
            this, this,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()

    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        viewModel.setDatePicked(year, month, dayOfMonth)
    }

    fun onSaveButtonClicked(v: View) {
        viewModel.saveBudget(userId)
        finish()
    }

    fun onCancelButtonClicked(v: View) {
        finish()
    }


}
