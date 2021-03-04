package com.juanbas.ekonomin.dataBase.repositories

import android.app.Application
import com.juanbas.ekonomin.dataBase.EkonominDataBase

/** Super class from which all other repositories inherit. */
open class Repository(application: Application){
    val ekonominDataBase = EkonominDataBase.getInstance(application)
}