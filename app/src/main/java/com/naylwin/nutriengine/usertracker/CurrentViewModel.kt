package com.naylwin.nutriengine.usertracker

import android.util.Log
import androidx.lifecycle.ViewModel
import com.naylwin.eatrition.database.UserActivity
import com.naylwin.eatrition.database.UserActivityDao
import kotlinx.coroutines.*

class CurrentViewModel(
    private val userActivityDao: UserActivityDao,
    private val name: String,
    private val date: String): ViewModel() {

    private val viewModelJob = Job()
    private val uiScope= CoroutineScope(Dispatchers.Main + viewModelJob)



    private suspend fun getUserActivity(name: String, date: String): UserActivity?{
        return withContext(Dispatchers.IO){
            userActivityDao.getUserActivity(name, date)
        }
    }

    private fun dummyUser(name: String, date: String): UserActivity {
        return UserActivity(name, date)
    }

    fun updateActivity(name: String, date: String) : UserActivity?{
        val currentActivity = runBlocking{
            getUserActivity(name, date)
           // Log.d("CurrentViewModel:" , "In UpdateBlock ${currentActivity}")
        }
        return currentActivity
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("CurrentViewModel", "Destroyed")
        viewModelJob.cancel()
    }
}