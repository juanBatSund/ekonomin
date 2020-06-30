package com.juanbas.ekonomin.navigationWrapper.budget

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.juanbas.ekonomin.navigationWrapper.budget.expenses.ExpensesFragment
import com.juanbas.ekonomin.navigationWrapper.budget.income.IncomeFragment

class IncomeExpenseAdapter(fragmentActivity: FragmentActivity):FragmentStateAdapter(fragmentActivity)  {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
       when(position){
           0 -> return IncomeFragment()
           1 -> return ExpensesFragment()
           else -> return IncomeFragment()
       }
    }
}