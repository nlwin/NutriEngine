package com.naylwin.nutriengine.usertracker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.naylwin.eatrition.database.UserActivityDao

class LoginViewModelFactory(private val userActivityDao: UserActivityDao)
    :ViewModelProvider.Factory{
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(userActivityDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

