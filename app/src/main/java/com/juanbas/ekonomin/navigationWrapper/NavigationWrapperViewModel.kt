package com.juanbas.ekonomin.navigationWrapper

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.juanbas.ekonomin.dataBase.Entities.UserEntity
import com.juanbas.ekonomin.dataBase.Repositories.UserRepository
/** Handles logic not associated with UI from */
class NavigationWrapperViewModel(application: Application) : AndroidViewModel(application) {

    companion object {
        private const val NAVIGATION_WRAPPER_TAG = "NavigationWrapperTag"

    }

}
