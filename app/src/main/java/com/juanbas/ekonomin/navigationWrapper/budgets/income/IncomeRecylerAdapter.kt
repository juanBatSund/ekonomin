package com.juanbas.ekonomin.navigationWrapper.budgets.income

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juanbas.ekonomin.R
import com.juanbas.ekonomin.dataBase.Entities.IncomeEntity
import kotlinx.android.synthetic.main.income_recycler_item_row.view.*


/** Adapter used to populate the income instances from the database into view holders. */
class IncomeRecyclerAdapter: RecyclerView.Adapter<IncomeRecyclerAdapter.IncomeViewHolder>() {

    private var incomes: ArrayList<IncomeEntity> = ArrayList()

    /** Assigns each income entity in the [icomes] list into a view holder */
    override fun onBindViewHolder(holder: IncomeViewHolder, position: Int) {
        holder.incomeEntity = incomes.get(position)
    }

    /** Returns size from income list */
    override fun getItemCount() = incomes.size

    /** Inflate a layout instance for each holder */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IncomeViewHolder =
        IncomeViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.income_recycler_item_row, parent, false)
        )

    /** Used to load items passed by the LiveData observer */
    fun loadItems(newItems: ArrayList<IncomeEntity>) {
        incomes = newItems
        this.notifyDataSetChanged()
    }

    /** TODO: Not yet implemented, but will be in future commits. */
    fun removeAt(position: Int) {
        val productToDelete = incomes.get(position)
        //incomeViewModel.deleteBudget(productToDelete)
        incomes.removeAt(position)
        notifyItemRemoved(position)
    }

    /** Defines the view holder object and what to do with it. */
    inner class IncomeViewHolder(var v: View) : RecyclerView.ViewHolder(v) {
        var incomeEntity: IncomeEntity? = null
            set(value) {
                field = value
                v.incomeOwner.text="${incomeEntity?.incomeOwner}"
                v.incomeValue.text = "${incomeEntity?.incomeValue}"
            }

    }

}
