package com.juanbas.ekonomin.navigationWrapper.budget.expenses

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.juanbas.ekonomin.dataBase.Repositories.ExpenseRepository
import com.juanbas.ekonomin.R
import com.juanbas.ekonomin.dataBase.Entities.ExpenseEntity
import com.juanbas.ekonomin.dataBase.Entities.IncomeEntity
import com.juanbas.ekonomin.dataBase.Repositories.BudgetRepository
import com.juanbas.ekonomin.navigationWrapper.budget.income.IncomeRecyclerAdapter
import kotlinx.android.synthetic.main.expense_add_dialog.view.*

/** Used to handle insertion of new expenses. */
class ExpensesFragment : Fragment() {

    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var recyclerView: RecyclerView
    val budgetId by lazy { BudgetRepository.budgetEntity.budgetId }

    private val expenseViewModel by lazy {
        ViewModelProviders.of(this).get(ExpenseViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.budget_expense_fragment,
            container,
            false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewAdapter = ExpenseRecyclerAdapter()
        viewManager = LinearLayoutManager(activity?.applicationContext)
        recyclerView = view.findViewById<RecyclerView>(R.id.expenseListRecycler).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
        loadAdapter()
        val addIncomeButton = view.findViewById<Button>(R.id.add_expense_button)
        addIncomeButton.setOnClickListener {
            addExpense()
        }
    }

    /** Loads adapter with incomes list */
    private fun loadAdapter() {
        val observer = Observer<List<ExpenseEntity>> { expenses ->
            val adapter = viewAdapter as ExpenseRecyclerAdapter
            adapter.loadItems(expenses as ArrayList<ExpenseEntity>)
        }
        expenseViewModel.getExpensesByBudgetId(budgetId)?.observe(viewLifecycleOwner, observer)

    }

    /** Adds starts add income dialog. */
    private fun addExpense() {
        // Alert dialog.
        val builder = AlertDialog.Builder(activity)
        builder.setTitle("Add the income")

        // Inflate Layout and assign it to the builder.
        val addExpenseLayout = layoutInflater.inflate(R.layout.expense_add_dialog, null)
        builder.setView(addExpenseLayout)

        // Dialog buttons.
        builder.setPositiveButton("Save") { dialog, which ->
            val expenseName = addExpenseLayout.expense.text.toString()
            val expenseValue = addExpenseLayout.expenseValue.text.toString().toDouble()
            val expenseCategory = "Job" // To do: take this from layout
            val expenseEntity = ExpenseEntity(null, budgetId, expenseName, expenseValue, expenseCategory)
            expenseViewModel.insertExpense(expenseEntity)
        }

        // Show dialog.
        builder.show()
}
}