package com.juanbas.ekonomin.navigationWrapper.budgets

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.juanbas.ekonomin.R
import com.juanbas.ekonomin.dataBase.entities.BudgetEntity
import com.juanbas.ekonomin.dataBase.repositories.BudgetRepository
import com.juanbas.ekonomin.databinding.FragmentBudgetListBinding
import java.util.*

/* Handles the list of budgets retrieved from data base. */
class BudgetFragmentList : Fragment() {

    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var recyclerView: RecyclerView
    private val BUDGET_FRAGMENT_LIST_TAG = "BudgetFragmentList"
    private var viewBinding: FragmentBudgetListBinding? = null
    private val budgetRepository by lazy { BudgetRepository(requireActivity().application) }
    private val args: BudgetFragmentListArgs by navArgs()

    private lateinit var userId: String
    val budgetListViewModel: BudgeListViewModel by lazy {
        ViewModelProvider(this).get(BudgeListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        userId = args.userId
        Log.d(BUDGET_FRAGMENT_LIST_TAG, "User id: $userId")
        viewBinding = FragmentBudgetListBinding.inflate(inflater, container, false)
        return viewBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createRecyclerView(view)
        observerAndLoadNewBudgets()
        viewBinding!!.addBudgetButton.setOnClickListener {
            addNewBudget()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }

    private fun createRecyclerView(view: View) {
        viewAdapter = BudgetRecyclerAdapter(budgetListViewModel)
        viewManager = LinearLayoutManager(activity?.applicationContext)
        recyclerView = view.findViewById<RecyclerView>(R.id.budgeListRecycler).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    private fun observerAndLoadNewBudgets() {
        val observer = Observer<List<BudgetEntity>> { budgets ->
            val adapter = viewAdapter as BudgetRecyclerAdapter
            adapter.loadItems(budgets as ArrayList<BudgetEntity>)
        }
        budgetListViewModel.getAllBudgetsByOwnerId(userId)?.observe(viewLifecycleOwner, observer)
    }

    private fun addNewBudget() {
        val dateDialogListener = dateListener()
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val dateDialog = DatePickerDialog(
            requireContext(),
            R.style.CustomDatePickerDialog,
            dateDialogListener, year, month, day
        )
        dateDialog.setTitle(getString(R.string.date_picker_title))
        dateDialog.show()
    }

    private fun dateListener(): DatePickerDialog.OnDateSetListener {
        return DatePickerDialog.OnDateSetListener { _ , year, month, dayOfMonth ->
            Log.d(BUDGET_FRAGMENT_LIST_TAG, "Date choosen: $year/${month + 1}/$dayOfMonth")
            val budgetEntity = BudgetEntity(null, userId, month + 1, year, dayOfMonth)
            budgetRepository.insertBudget(budgetEntity)
        }
    }
}