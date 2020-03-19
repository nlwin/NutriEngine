package com.naylwin.nutriengine.usertracker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.naylwin.eatrition.database.UserActivityDao

class CurrentViewModelFactory(
    private val userActivityDao: UserActivityDao,
    private val name : String,
    private val date: String): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CurrentViewModel::class.java)) {
            return CurrentViewModel(userActivityDao, name, date) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}