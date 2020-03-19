package com.naylwin.nutriengine.usertracker

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.naylwin.eatrition.database.UserActivityDao

class HistoryViewModelFactory(
    private val userActivityDao: UserActivityDao,
    private val context: Context): ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HistoryViewModel::class.java)) {
            return HistoryViewModel(userActivityDao, context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}