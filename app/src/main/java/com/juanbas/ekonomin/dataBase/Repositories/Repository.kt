package com.juanbas.ekonomin.dataBase.Repositories

import android.app.Application
import com.juanbas.ekonomin.dataBase.EkonominDataBase

open class Repository(application: Application){
    val ekonominDataBase = EkonominDataBase.getInstance(application)
}