package com.juanbas.ekonomin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.juanbas.ekonomin.DataBase.ViewModels.BudgetViewModel
import com.juanbas.ekonomin.NavigationWrapper.IncomeFragment
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    val budgetViewModel: BudgetViewModel by lazy {
        ViewModelProviders.of(this).get(BudgetViewModel::class.java)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_sallary -> {
                supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container_budget, IncomeFragment())
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        createSignIntent()
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container_budget,
                IncomeFragment()
            ).commit()
        }
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }


    private fun createSignIntent() {
        // choose authentication providers
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build(),
            AuthUI.IdpConfig.FacebookBuilder().build()
        )

        // create and launch sign-in intent
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setLogo(R.drawable.money_icon_images_11) // Set logo drawable
                .setTosAndPrivacyPolicyUrls(
                    "https://example.com/terms.html",
                    "https://example.com/privacy.html"
                )
                .build(),
            RC_SIGN_IN
        )

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == Activity.RESULT_OK) {
                // Successfully signed in
                val user = FirebaseAuth.getInstance().currentUser
                //Log the user just to demonstrate, these lines bellow will be taken off after
                Log.d("USERFIRE", "USer id is ${user?.uid},  ${user?.displayName}, ${user?.email} ")

                /* //To be removed:
                 val budgetToRegister = BudgetEntity(null,user!!.uid,"My budget")
                 budgetViewModel.insert(budgetToRegister)


                 budgetViewModel?.getAllBudgetss()?.observe(this, Observer<List<BudgetEntity>> { budgets ->
                     budgets as ArrayList
                     budgets.forEach{ if(it.ownerId==user?.uid) Log.d("USERFIRE","Id from db: ${it.budgetId}") }

                     })*/

            } else {
                Log.d("USERFIRE", "Sign in failed")

            }
        }
    }


    private fun signOut() {
        AuthUI.getInstance()
            .signOut(this)
            .addOnCompleteListener {
                Log.d("USERFIRE", "Signed out")
                createSignIntent()
            }
    }

    private fun delete() {
        AuthUI.getInstance()
            .delete(this)
            .addOnCompleteListener {
                Log.d("USERFIRE", "deleted")

            }
    }

    fun onSignOutClick(item: MenuItem): Boolean {
        signOut()
        return true
    }

    companion object {

        private const val RC_SIGN_IN = 123
    }

}
