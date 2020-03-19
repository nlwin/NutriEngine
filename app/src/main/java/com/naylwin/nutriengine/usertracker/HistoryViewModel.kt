package com.naylwin.nutriengine.usertracker

import android.content.Context
import androidx.lifecycle.ViewModel
import com.naylwin.eatrition.database.UserActivity
import com.naylwin.eatrition.database.UserActivityDao
import kotlinx.coroutines.*

class HistoryViewModel(
    private val userActivityDao: UserActivityDao,
    private val context: Context
): ViewModel() {
    private var viewModelJob: Job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun getUserActivities(name: String): List<UserActivity?>{
       return runBlocking {
           getActvities(name)
       }
    }

    private suspend fun getActvities(name: String): List<UserActivity?>{
        return withContext(Dispatchers.IO){
            userActivityDao.getUserActivities(name)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}