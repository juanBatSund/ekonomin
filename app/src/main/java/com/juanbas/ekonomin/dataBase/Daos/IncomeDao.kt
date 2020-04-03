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

    @Query("DELETE FROM income_table")
    fun deleteAllIncomes()

    @Query("SELECT * FROM income_table WHERE budgetId=:id")
    fun getIncomeByBudgetId(id:Int?): LiveData<List<IncomeEntity>>

    @Query("SELECT * FROM income_table ORDER BY budgetId DESC")
    fun getAllIncomes(): LiveData<List<IncomeEntity>>
}