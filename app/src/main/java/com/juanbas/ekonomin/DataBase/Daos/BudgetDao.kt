package com.juanbas.ekonomin.DataBase.Daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.juanbas.ekonomin.DataBase.Entities.BudgetEntity

@Dao
interface BudgetDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBudget(budget: BudgetEntity)

    @Update()
    fun updateBudget(budget: BudgetEntity)

    @Delete()
    fun deleteBudget(budget: BudgetEntity)

    @Query("DELETE FROM budget_table")
    fun deleteAllBudgets()

    @Query("SELECT * FROM budget_table WHERE ownerId=:id")
    fun getBudgetByOwnerId(id: String): LiveData<BudgetEntity>

    @Query("SELECT * FROM budget_table ORDER BY budgetName DESC")
    fun getAllBudgets(): LiveData<List<BudgetEntity>>

}