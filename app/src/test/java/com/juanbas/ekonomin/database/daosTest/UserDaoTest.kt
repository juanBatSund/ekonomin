package com.juanbas.ekonomin.database.daosTest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.juanbas.ekonomin.dataBase.EkonominDataBase
import com.juanbas.ekonomin.dataBase.daos.UserDao
import com.juanbas.ekonomin.dataBase.entities.UserEntity
import com.juanbas.ekonomin.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@ExperimentalCoroutinesApi
@SmallTest
@RunWith(AndroidJUnit4::class)
class UserDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: EkonominDataBase
    private lateinit var userDao: UserDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            EkonominDataBase::class.java
        ).allowMainThreadQueries().build()
        userDao = database.userDao()
    }

    @Test
    fun insetUser() = runBlockingTest {
        val user = UserEntity("1", "Jony", true)
        userDao.insertUser(user)
        val usersList = userDao.getAllUsers().getOrAwaitValue()
        assertThat(usersList).contains(user)
    }

    @Test
    fun deleteUser() = runBlockingTest {
        val user = UserEntity("1", "Jony", true)
        userDao.insertUser(user)
        userDao.deleteUser(user)
        val userList = userDao.getAllUsers().getOrAwaitValue()
        assertThat(userList).doesNotContain(userList)
    }

    @Test
    fun updateUser() = runBlockingTest {
        val user = UserEntity("1", "Jony", true)
        userDao.insertUser(user)
        val updatedUser = UserEntity("1", "Jonysk", false)
        userDao.updateUser(updatedUser)
        val userList = userDao.getAllUsers().getOrAwaitValue()
        assertThat(userList).doesNotContain(user)
        assertThat(userList).containsExactly(updatedUser)
    }

    @Test
    fun deleAllUsers() = runBlockingTest {
        val user1 = UserEntity("1", "Jony", true)
        val user2 = UserEntity("2", "Mary", true)
        val user3 = UserEntity("4", "karl", true)
        userDao.insertUser(user1)
        userDao.insertUser(user2)
        userDao.insertUser(user3)
        userDao.deleteAllUsers()
        val userList = userDao.getAllUsers().getOrAwaitValue()
        println("${userList.size}")
        assertThat(userList).isEmpty()
    }

    @After
    fun teardown() {
        database.close()
    }

}