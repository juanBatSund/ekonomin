package com.juanbas.ekonomin.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import com.firebase.ui.auth.IdpResponse

/** Used to launch login requesto to firebase. */
class LoginResultContract(val intent: Intent) : ActivityResultContract<Int, IdpResponse>() {

    override fun createIntent(context: Context, input: Int?): Intent {
        return intent
    }

    override fun parseResult(resultCode: Int, intent: Intent?): IdpResponse? = when (resultCode) {
        Activity.RESULT_OK -> IdpResponse.fromResultIntent(intent)
        else -> null
    }
}