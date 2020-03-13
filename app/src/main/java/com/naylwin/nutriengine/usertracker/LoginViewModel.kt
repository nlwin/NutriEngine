package com.naylwin.nutriengine.usertracker

import androidx.lifecycle.ViewModel
import com.naylwin.eatrition.database.UserActivity
import com.naylwin.eatrition.database.UserActivityDao
import kotlinx.coroutines.*

class LoginViewModel(private var userActivityDao: UserActivityDao) : ViewModel() {
    private var viewModelJob: Job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun logInAction(name: String, date: String){
        uiScope.launch {
            val userExits = getUser(name, date)
            if(!userExits)
                insert(name, date)
        }
    }

    // check user with specific date exist
    private suspend fun getUser(name: String, date: String): Boolean{
        return withContext(Dispatchers.IO){
            var userActivity: UserActivity? = userActivityDao.getUserActivity(name, date)
            userActivity != null
        }
    }

    // insert new user activity for specific date
    private suspend fun insert(name: String, date: String){
        withContext(Dispatchers.IO){
            userActivityDao.insert(UserActivity(user_name = name, date=date))
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}