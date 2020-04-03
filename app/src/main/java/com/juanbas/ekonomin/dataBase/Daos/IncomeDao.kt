package com.juanbas.ekonomin.dataBase.Daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.juanbas.ekonomin.dataBase.Entities.IncomeEntity

@Dao
interface IncomeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIncome(incomeEntity: IncomeEntity)

    @Update()
    fun updateIncome(incomeEntity: IncomeEntity)

    @Delete()
    fun deleteIncome(incomeEntity: IncomeEntity)

    @Query("DELETE FROM ")
}