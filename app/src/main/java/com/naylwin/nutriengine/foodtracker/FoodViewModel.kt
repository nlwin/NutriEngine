package com.naylwin.nutriengine.foodtracker

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.naylwin.eatrition.database.Food
import com.naylwin.eatrition.database.FoodDao
import com.naylwin.eatrition.database.UserActivityDao
import kotlinx.coroutines.*

class FoodViewModel(
    private val foodDao: FoodDao,
    private val userActivityDao: UserActivityDao,
    private val context : Context
) : ViewModel() {
    private var viewModelJob: Job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    fun getFood(foodName: String): List<Food?> {
        val foodList = runBlocking {
           getPossibleFoodList(foodName)
        }
        Log.d("FoodViewModel", "${foodList.size}")
        return foodList
    }

    private suspend fun getPossibleFoodList(foodName: String): List<Food?>{
        return withContext(Dispatchers.IO){
            foodDao.findFoods(foodName)
        }
    }

    fun addFood(userName: String, date: String, foodID: Int){
        uiScope.launch{
            val food = findFoodByID(foodID)
            if (food != null) {
                updateUserActivity(userName, date, food)
                Toast.makeText(context, "Succeessfully Add Food", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private suspend fun findFoodByID(foodID: Int) : Food? {
        return withContext(Dispatchers.IO) {
            val food = foodDao.getFoodStats(foodID)
            food
        }
    }

    private suspend fun updateUserActivity(userName: String, date: String, food: Food) {
        withContext(Dispatchers.IO) {
            val userActivity = userActivityDao.getUserActivity(userName, date)
            if (userActivity != null) {
                userActivity.apply {
                    calories += food.calories
                    sugar += food.sugar
                    sodium += food.sodium
                    vit_a += food.vit_a
                    vit_c += food.vit_c
                }
                userActivityDao.update(userActivity)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("FoodViewModel", "it is destroyed------------------")
        viewModelJob.cancel()
    }

}