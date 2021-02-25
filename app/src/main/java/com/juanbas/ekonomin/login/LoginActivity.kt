package com.juanbas.ekonomin.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.juanbas.ekonomin.R
import com.juanbas.ekonomin.databinding.ActivityLoginBinding
import com.juanbas.ekonomin.navigationWrapper.NavigationWrapper


/** Login activity used with Firebase authentication. */
class LoginActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityLoginBinding
    private val loginViewModel by lazy { ViewModelProvider(this).get(LoginViewModel::class.java) }

    companion object {
        private const val RC_SIGN_IN = 123
        private const val INPUT_INT = "input_int"
        private const val USER_ID = "userId"
        private const val USER_NAME = "userName"
        private const val USER_INFO = "userInfo"
        private const val LOGIN_ERROR = "Could not login"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        createSignIntent()
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
            .build().apply { putExtra(INPUT_INT, "input_int") }
    }

    private fun authenticationRequest(intent: Intent) =
        registerForActivityResult(LoginResultContract(intent)) { response: IdpResponse? ->
            handleResponse(response)
        }


    private fun handleResponse(response: IdpResponse?) {
        if (response == null || response.error != null) {
            Toast.makeText(
                this, LOGIN_ERROR,
                Toast.LENGTH_LONG
            ).show();
        } else {
            /* Handle sign-in success from returned data. */
            val user = FirebaseAuth.getInstance().currentUser
            val userID = user?.uid.toString()
            val userName = user?.displayName.toString()
            loginViewModel.registerUser(userName, userID)
            val userBundle = bundleOf(Pair(USER_ID, userID), Pair(USER_NAME, userName))
            val intent = Intent(this, NavigationWrapper::class.java)
                .apply { putExtra(USER_INFO, userBundle) }
            startActivity(intent)
            finish()
        }
    }
}