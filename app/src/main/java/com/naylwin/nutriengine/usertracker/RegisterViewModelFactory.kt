package com.naylwin.nutriengine.usertracker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.naylwin.eatrition.database.UserDao

class RegisterViewModelFactory(private val userDao: UserDao): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(userDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}