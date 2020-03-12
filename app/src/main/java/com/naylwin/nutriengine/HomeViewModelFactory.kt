package com.naylwin.nutriengine

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.naylwin.eatrition.database.FoodDao

class HomeViewModelFactory(
    private val foodDao: FoodDao, private val context: Context) : ViewModelProvider.Factory{
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(foodDao, context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}