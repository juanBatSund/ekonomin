package com.juanbas.ekonomin.navigationWrapper

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.juanbas.ekonomin.R
import com.juanbas.ekonomin.dataBase.Entities.BudgetEntity
import com.juanbas.ekonomin.dataBase.Repositories.BudgetRepository
import com.juanbas.ekonomin.dataBase.Repositories.UserRepository
import com.juanbas.ekonomin.navigationWrapper.budget.BudgetRecyclerAdapter
import com.juanbas.ekonomin.navigationWrapper.budget.BudgetViewModel
import java.util.*

/* Handles the list of budgets retrieved from data base*/
class BudgetFragmentList : Fragment() {

    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var recyclerView: RecyclerView
    private val BUDGET_FRAGMENT_LIST_TAG = "BudgetFragmentList"
    private val budgetRepository by lazy { BudgetRepository(activity!!.application) }

    val budgetDataViewModel: BudgetViewModel by lazy {
        ViewModelProviders.of(this).get(BudgetViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val inflater_helper = inflater.inflate(R.layout.fragment_budget_list, container, false)
        return inflater_helper

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewAdapter = BudgetRecyclerAdapter(budgetDataViewModel)
        viewManager = LinearLayoutManager(activity?.applicationContext)
        recyclerView = view.findViewById<RecyclerView>(R.id.budgeListRecycler).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
        loadAdapter()
        val addBudgetButton = view.findViewById<Button>(R.id.add_budget_button)
        addBudgetButton.setOnClickListener {
            createBudget()
            /*Todo: user the code below when pressing on the newly created budget.
             val intent = Intent(activity, BudgetView::class.java)
             startActivity(intent)*/
        }

    }

    private fun loadAdapter() {
        budgetDataViewModel.getAllBudgetss()
            ?.observe(viewLifecycleOwner, Observer<List<BudgetEntity>> { budgets ->
                val adapter = viewAdapter as BudgetRecyclerAdapter
                adapter.loadItems(budgets as ArrayList<BudgetEntity>)
            })
    }

    private fun createBudget() {
        val dateDialogListener =
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                Log.d(BUDGET_FRAGMENT_LIST_TAG, "Date choosen: $year/${month + 1}/$dayOfMonth")
                val userId = UserRepository.sessionUser.userId
                val budgetEntity = BudgetEntity(null, userId, month + 1, year)
                budgetRepository.insertBudget(budgetEntity)
            }

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val dateDialog = DatePickerDialog(
            activity,
            R.style.CustomDatePickerDialog,
            dateDialogListener, year, month, day
        )
        dateDialog.setTitle(getString(R.string.date_picker_title))
        dateDialog.show()
    }

}