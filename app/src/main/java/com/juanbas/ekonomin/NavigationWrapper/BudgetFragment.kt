package com.juanbas.ekonomin.NavigationWrapper

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.juanbas.ekonomin.R
import kotlinx.android.synthetic.main.activity_login.*

class BudgetFragment: Fragment() {

   override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       super.onCreateView(inflater, container, savedInstanceState)
       val layoutInflated = inflater.inflate(R.layout.fragment_budget, fragment_container_budget, false)

       return layoutInflated
    }
}