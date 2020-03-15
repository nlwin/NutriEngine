package com.naylwin.nutriengine.usertracker

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.naylwin.eatrition.database.UserActivity
import com.naylwin.eatrition.database.UserActivityDao
import kotlinx.coroutines.*

class CurrentViewModel(
    private val userActivityDao: UserActivityDao,
    private val name: String,
    private val date: String): ViewModel() {

    lateinit var currentActivity: LiveData<UserActivity?>
    private val viewModelJob = Job()
    private val uiScope= CoroutineScope(Dispatchers.Main + viewModelJob)

    init{
        uiScope.launch {
            currentActivity = userActivityDao.getLiveUserActivity(name, date)
//            if (currentActivity.value == null) {
//                currentActivity = dummyUser(name, date)
//            }
        }
    }

    private suspend fun getUserActivity(name: String, date: String): UserActivity?{
        return withContext(Dispatchers.IO){
            userActivityDao.getUserActivity(name, date)
        }
    }

    private fun dummyUser(name: String, date: String): UserActivity {
        return UserActivity(name, date)
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("CurrentViewModel", "Destroyed")
        viewModelJob.cancel()
    }
}