package com.juanbas.ekonomin.dataBase.Daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.juanbas.ekonomin.dataBase.Entities.BudgetEntity

/** Dao used to retrieve the [BudgetEntity] instances from the database. */
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

    @Query("SELECT * FROM budget_table WHERE userId=:id")
    fun getBudgetByOwnerId(id: String): LiveData<BudgetEntity>

    @Query("SELECT * FROM budget_table ORDER BY budgetId DESC")
    fun getAllBudgets(): LiveData<List<BudgetEntity>>

    @Query("SELECT * FROM budget_table WHERE userId=:id ORDER BY budgetId DESC")
    fun getAllBudgetsByOwnerId(id: String): LiveData<List<BudgetEntity>>

}