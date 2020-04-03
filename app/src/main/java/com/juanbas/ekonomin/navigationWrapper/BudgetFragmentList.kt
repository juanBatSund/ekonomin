package com.juanbas.ekonomin.navigationWrapper

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.juanbas.ekonomin.R
import com.juanbas.ekonomin.dataBase.Entities.BudgetEntity
import com.juanbas.ekonomin.navigationWrapper.budget.BudgetRecyclerAdapter
import com.juanbas.ekonomin.navigationWrapper.budget.BudgetView
import com.juanbas.ekonomin.navigationWrapper.budget.BudgetViewModel

/* Handles the list of budgets retrieved from data base*/
class BudgetFragmentList : Fragment() {

    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var recyclerView: RecyclerView
    private val args: BudgetFragmentListArgs by navArgs<BudgetFragmentListArgs>()
    private val BUDGET_FRAGMENT_LIST_TAG = "BudgetFragmentList"

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
        Log.d(BUDGET_FRAGMENT_LIST_TAG, "Id: ${args.userId}")
        val addBudgetButton = view.findViewById<Button>(R.id.add_budget_button)
        addBudgetButton.setOnClickListener {
            val intent = Intent(activity, BudgetView::class.java)
            intent.putExtra("userId", args.userId)
            startActivity(intent)
        }

    }

    private fun loadAdapter() {
        budgetDataViewModel.getAllBudgetss()
            ?.observe(viewLifecycleOwner, Observer<List<BudgetEntity>> { budgets ->
                val adapter = viewAdapter as BudgetRecyclerAdapter
                adapter.loadItems(budgets as ArrayList<BudgetEntity>)
            })

    }
}