package com.juanbas.ekonomin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.juanbas.ekonomin.dataBase.Entities.UserEntity
import com.juanbas.ekonomin.dataBase.Repositories.UserRepository
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private val LOGIN_ACTIVITY_TAG = "LoginActivityTag"
    private val userRepository: UserRepository by lazy { UserRepository(application) }
    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_Budgets -> {
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
                val userId = user?.uid.toString()
                val userName = user?.displayName.toString()
                Log.d("USERFIRE", "USer id is $userId,  $userName ")

                //Create entity instance for user and assing to global value
                val currentSessionUser = UserEntity(userId, userName)
                UserRepository.sessionUser = currentSessionUser

                // Insert or update the database
                var userRetrieved = userRepository.getUserById(userId)
                if (userRetrieved == null) {
                    userRepository.insertUser(currentSessionUser)
                    Log.d(LOGIN_ACTIVITY_TAG, "User does not exist inserting")
                } else {
                    userRepository.updateUser(currentSessionUser)
                    Log.d(LOGIN_ACTIVITY_TAG, "User already exists updating")
                }
                findNavController(R.id.budget_list_nav_host_fragment)
                    .setGraph(R.navigation.home_navigation_graph)
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
