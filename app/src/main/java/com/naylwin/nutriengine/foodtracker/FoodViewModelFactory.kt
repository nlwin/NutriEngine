package com.naylwin.nutriengine.foodtracker

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.naylwin.eatrition.database.FoodDao
import com.naylwin.eatrition.database.UserActivityDao

class FoodViewModelFactory(
    private val foodDao: FoodDao,
    private val userActivityDao: UserActivityDao,
    private val context: Context
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FoodViewModel::class.java)) {
            return FoodViewModel(foodDao, userActivityDao, context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}