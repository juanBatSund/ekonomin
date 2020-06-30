package com.juanbas.ekonomin.navigationWrapper.budget.expenses

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juanbas.ekonomin.R
import com.juanbas.ekonomin.dataBase.Entities.ExpenseEntity
import com.juanbas.ekonomin.dataBase.Entities.IncomeEntity
import kotlinx.android.synthetic.main.expense_recycler_item_row.view.*

/** Adapter used to populate the expenses instances from the database into view holders. */
class ExpenseRecyclerAdapter: RecyclerView.Adapter<ExpenseRecyclerAdapter.ExpenseViewHolder>() {

    private var expenses: ArrayList<ExpenseEntity> = ArrayList()

    /** Inflate a layout instance for each holder. */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder =
        ExpenseViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.expense_recycler_item_row,parent,false))

    /** Returns size from expense list. */
    override fun getItemCount() = expenses.size

    /** Assigns each expense entity in the [expenses] list into a view holder. */
    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        holder.expenseEntity = expenses.get(position)
    }

    /** Used to load items passed by the LiveData observer */
    fun loadItems(newItems: ArrayList<ExpenseEntity>) {
        expenses = newItems
        this.notifyDataSetChanged()
    }

    /**TODO: To be implemented in upcomming commits. */
    fun removeAt(position: Int) {
        val productToDelete = expenses.get(position)
        expenses.removeAt(position)
        notifyItemRemoved(position)
    }

    /** Defines the view holder object and what to do with it. */
    inner class ExpenseViewHolder(var v: View): RecyclerView.ViewHolder(v){
        var expenseEntity: ExpenseEntity?=null
            set(value) {
                field=value
                v.expenseName.text="${expenseEntity?.expenseName}"
                v.expenseValue.text="${expenseEntity?.expenseValue}"
            }

    }

}