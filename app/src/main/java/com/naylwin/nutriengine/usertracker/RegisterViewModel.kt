package com.naylwin.nutriengine.usertracker

import androidx.lifecycle.ViewModel
import com.naylwin.eatrition.database.User
import com.naylwin.eatrition.database.UserDao
import kotlinx.coroutines.*

class RegisterViewModel(private val userDao: UserDao) : ViewModel(){
    private var viewModelJob: Job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun registerUser(name: String, age: Int, height: Int, weight: Int){
        uiScope.launch{
            insert(name, age, height,weight)
        }
    }

    private suspend fun insert(name: String, age: Int, height: Int, weight: Int){
        withContext(Dispatchers.IO){
            val user: User = User(name=name, age=age, height=height, weight=weight)
            userDao.insert(user)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
