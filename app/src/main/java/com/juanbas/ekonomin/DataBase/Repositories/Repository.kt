package com.juanbas.ekonomin.DataBase.Repositories

import android.app.Application
import com.juanbas.ekonomin.DataBase.EkonominDataBase

open class Repository(application: Application){
    val ekonominDataBase = EkonominDataBase.getInstance(application)
}