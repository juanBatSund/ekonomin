package com.juanbas.ekonomin.dataBase.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.juanbas.ekonomin.dataBase.entities.IncomeEntity

/** Dao used to retrieve the [IncomeEntity] instances from the database. */
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

    @Query("SELECT SUM(incomeValue) FROM income_table WHERE budgetId=:id")
    fun getTotalIncomeByBudgetId(id:Int?): LiveData<Double>

}