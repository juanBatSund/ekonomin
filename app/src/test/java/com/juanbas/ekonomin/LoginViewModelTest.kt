package com.juanbas.ekonomin

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.runners.MockitoJUnitRunner


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest {



    //val loginViewModel = LoginViewModel()
    //val userRepository = UserRepository(Application())

    @Test
    fun test_registerUser() {
        val userName = "juan"
        val userId = "jrsaks"
        val isLogged = true
       // loginViewModel.registerUser(userName, userId, isLogged)
        //Assert.assertTrue(userRepository.isUserLoggedIn(userId)?.value == true)
    }
}
