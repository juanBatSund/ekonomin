package com.juanbas.ekonomin.login

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.juanbas.ekonomin.R
import com.juanbas.ekonomin.databinding.ActivityLoginBinding
import com.juanbas.ekonomin.navigationWrapper.NavigationWrapperActivity


/** Login activity used with Firebase authentication. */
class LoginActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityLoginBinding
    private val loginViewModel by lazy { ViewModelProvider(this).get(LoginViewModel::class.java) }
    companion object {
        const val RC_SIGN_IN = 123
        const val INPUT_INT = "input_int"
        const val LOGIN_ERROR = "Could not login"
        const val USER_INFO = "userInfo"
        const val LOGIN_ACTIVITY_TAG = "loginActivityTag"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        if (savedInstanceState!=null) {
            loginViewModel.userInfo = savedInstanceState
        }else {
            createSignIntent()
        }
    }

    override fun onStop() {
        super.onStop()
        onSaveInstanceState(loginViewModel.userInfo)
    }

    private fun createSignIntent() {
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build()
        )
        val intent = createAuthenticationIntent(providers)
        authenticationRequest(intent).launch(RC_SIGN_IN)
    }

    private fun createAuthenticationIntent(providers: ArrayList<AuthUI.IdpConfig>): Intent {
        return AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .setLogo(R.drawable.money_icon_images_11) // Set logo drawable
            .setIsSmartLockEnabled(false)
            .build().apply { putExtra(INPUT_INT, INPUT_INT) }
    }

    private fun authenticationRequest(intent: Intent) =
        registerForActivityResult(LoginResultContract(intent)) { response: IdpResponse? ->
            handleResponse(response)
        }

    @SuppressLint("RestrictedApi")
    private fun handleResponse(response: IdpResponse?) {
        if (response == null ){
            finish()
            Log.d(LOGIN_ACTIVITY_TAG,
                "Back pressed or something wrong with request.")
        }else if (response.error != null || !response.isSuccessful ) {
            Log.d(LOGIN_ACTIVITY_TAG,
                "Response to request returned an error: ${response.error}.")
            Toast.makeText(
                this, LOGIN_ERROR,
                Toast.LENGTH_LONG
            ).show()
        } else {
            Log.d(LOGIN_ACTIVITY_TAG,
                "Login succeeded.")
            /* Handle sign-in success from returned data. */
            val user = FirebaseAuth.getInstance().currentUser
            val userId = user?.uid.toString()
            val userName = user?.displayName.toString()
            loginViewModel.registerUser(userName, userId, true)
            startNavigationWrapper()
        }
    }

    private fun startNavigationWrapper(){
        Log.d(LOGIN_ACTIVITY_TAG, "Starting navigation landing on budget list.")
        val intent = Intent(this, NavigationWrapperActivity::class.java)
            .apply { putExtra(USER_INFO, loginViewModel.userInfo) }
        startActivity(intent)
        finish()
    }
}