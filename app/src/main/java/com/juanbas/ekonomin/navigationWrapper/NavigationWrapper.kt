package com.juanbas.ekonomin.navigationWrapper

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.firebase.ui.auth.AuthUI
import com.juanbas.ekonomin.R
import com.juanbas.ekonomin.databinding.ActivityNavigationWrapperBinding
import com.juanbas.ekonomin.login.LoginActivity
/**
 * Handles instantiations of fragments and bottom navigation.
 * The first fragment is [BudgetFragmentList].
 */
class NavigationWrapper : AppCompatActivity() {
    companion object {
        const val USER_INFO = "userInfo"
    }

    private val navigationWrapperViewModel
            by lazy { ViewModelProvider(this).get(NavigationWrapperViewModel::class.java) }
    private lateinit var userInfo: Bundle
    private lateinit var viewBinding: ActivityNavigationWrapperBinding
    private val navController by lazy { findNavController(R.id.budget_list_nav_host_fragment) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityNavigationWrapperBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        userInfo = intent.getBundleExtra(USER_INFO)
        setBottomNavigationVisible()
        setBudgetListVisibleForUser()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    // Reacts to the sign out button press on the menu
    fun onSignOutClick(unused: MenuItem): Boolean {
        signOut()
        return true
    }

    private fun setBottomNavigationVisible() {
        val bottomNav = viewBinding.navigation
        bottomNav.setupWithNavController(navController)
    }

    private fun setBudgetListVisibleForUser() {
        navController.setGraph(R.navigation.home_navigation_graph, userInfo)
    }

    private fun signOut() {
        AuthUI.getInstance()
            .signOut(this)
            .addOnCompleteListener {
                Log.d("Sign out", "Signed out")
            }
        val loginIntent = Intent(this, LoginActivity::class.java)
        startActivity(loginIntent)
        finish()
        /* TODO: Remove the user from the database in the future when it is possible get all data
         from remote data source.*/
    }
}