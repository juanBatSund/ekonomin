package com.juanbas.ekonomin.navigationWrapper.budget.income

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juanbas.ekonomin.R
import com.juanbas.ekonomin.dataBase.Entities.BudgetEntity
import com.juanbas.ekonomin.navigationWrapper.budget.BudgetViewModel
import kotlinx.android.synthetic.main.budget_recycler_item_row.view.*


/** Adapter used to connect content from database into BudgetHolder view holder */
class IncomeRecyclerAdapter(
    val productModel: BudgetViewModel
) : RecyclerView.Adapter<IncomeRecyclerAdapter.IncomeHolder>() {

    private var incomes: ArrayList<BudgetEntity> = ArrayList()

    /** Assigns each Budget entity in the [budgets] list into a view holder */
    override fun onBindViewHolder(holder: IncomeHolder, position: Int) {
        holder.incomeEntity = incomes.get(position)
    }

    /** Returns size from budgets list */
    override fun getItemCount() = incomes.size

    /** Inflate a layout instance for each holder */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IncomeHolder =
        IncomeHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.budget_recycler_item_row, parent, false)
        )

    /** Used to load items passed by the LiveData observer */
    fun loadItems(newItems: ArrayList<BudgetEntity>) {
        incomes = newItems
        this.notifyDataSetChanged()
    }

    fun removeAt(position: Int) {
        val productToDelete = incomes.get(position)
        productModel.deleteBudget(productToDelete)
        incomes.removeAt(position)
        notifyItemRemoved(position)
    }

    /** Defines the view holder object and what to do with it. */
    class IncomeHolder(var v: View) : RecyclerView.ViewHolder(v) {
        var incomeEntity: BudgetEntity? = null
            set(value) {
                field = value
                var date = "${value?.dueYear}/${value?.dueMonth}"
                v.referenceDate.text = date
            }
    }

}
