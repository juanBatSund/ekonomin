package com.juanbas.ekonomin.navigationWrapper.budget.income

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.juanbas.ekonomin.R
import com.juanbas.ekonomin.dataBase.Entities.BudgetEntity
import com.juanbas.ekonomin.dataBase.Entities.IncomeEntity
import com.juanbas.ekonomin.dataBase.Repositories.BudgetRepository
import com.juanbas.ekonomin.dataBase.Repositories.IncomeRepository
import kotlinx.android.synthetic.main.income_recycler_item_row.view.*

/** Handles income list. */
class IncomeFragment : Fragment() {
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var recyclerView: RecyclerView
    private val inComeRepository by lazy{ IncomeRepository(activity!!.application)}
    val budgetId by lazy {BudgetRepository.budgetEntity.budgetId}

    companion object {
        fun newInstance() = IncomeFragment()
    }

    private val incomeViewModel by lazy {ViewModelProviders.of(this).get(IncomeViewModel::class.java)}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.budget_income_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewAdapter = IncomeRecyclerAdapter(incomeViewModel)
        viewManager = LinearLayoutManager(activity?.applicationContext)
        recyclerView = view.findViewById<RecyclerView>(R.id.incomeListRecycler).apply{
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
        loadAdapter()
        val addIncomeButton = view.findViewById<Button>(R.id.add_income_button)
        addIncomeButton.setOnClickListener {
            addIncome()
        }
    }

    /** Loads adapter with incomes list */
    private fun loadAdapter(){
        val observer = Observer<List<IncomeEntity>>{ incomes ->
            val adapter = viewAdapter as IncomeRecyclerAdapter
            adapter.loadItems(incomes as ArrayList<IncomeEntity>)
        }
        incomeViewModel.getAllIncomeByBudgetId(budgetId)?.observe(viewLifecycleOwner,observer )

    }

    /** Adds starts add income dialog. */
    private fun addIncome(){
        // Alert dialog.
        val builder = AlertDialog.Builder(activity)
        builder.setTitle("Add the income")

        // Inflate Layout and assign it to the builder.
        val addIncomeLayout = layoutInflater.inflate(R.layout.income_add_dialog,null)
        builder.setView(addIncomeLayout)

        // Dialog buttons.
        builder.setPositiveButton("Save"){dialog, which ->
            val incomeOwner = addIncomeLayout.incomeOwner.text.toString()
            val incomeValue = addIncomeLayout.incomeValue.text.toString().toDouble()
            val incomeSource = "Job" // To do: take this from layout
            val inComeEntity = IncomeEntity(null,budgetId,incomeOwner, incomeValue,incomeSource)
            inComeRepository.insertIncome(inComeEntity)
        }

        // Show dialog.
        builder.show()


    }

}
