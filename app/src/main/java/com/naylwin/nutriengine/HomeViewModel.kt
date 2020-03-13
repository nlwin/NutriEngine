package com.naylwin.nutriengine

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.naylwin.eatrition.database.Food
import com.naylwin.eatrition.database.FoodDao
import com.opencsv.CSVReader
import kotlinx.coroutines.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class HomeViewModel(val foodDao: FoodDao, val context: Context) : ViewModel() {

    private var viewModelJob: Job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun addFoodsToDataBase() {
        uiScope.launch {
            val foodExist = checkExist()
            if(foodExist)
                Toast.makeText(context, "Food Already Exist", Toast.LENGTH_SHORT).show()
            else {
                writingToDatabase()
                Toast.makeText(context, "Finish Adding Foods", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private suspend fun checkExist(): Boolean {
        return withContext(Dispatchers.IO) {
            val food = foodDao.findFirstFood()
            if(food != null)
                true
            else
                false
        }
    }


    private suspend fun writingToDatabase() {
        withContext(Dispatchers.IO) {

            try {
                val csvReader = CSVReader(BufferedReader(InputStreamReader(context.resources.openRawResource(R.raw.food_database))))
                var foodId: Int = -1
                var line = csvReader.readNext()
                while (line != null) {
                    foodId += 1
                    if (foodId <= 0) {
                        line = csvReader.readNext()
                        continue
                    }
                    val foodDes: String = line[0].toString()
                    val calories: Int = if (line[1] != "") line[1].toInt() else 0
                    val sugar: Float = if (line[2] != "") line[2].toFloat() else 0F
                    val sodium: Int = if (line[3] != "") line[3].toInt() else 0
                    val vit_c: Float = if (line[4] != "") line[4].toFloat() else 0F
                    val vit_a: Int = if (line[5] != "") line[5].toInt() else 0
                    val food: Food =
                        Food(foodId, foodDes, calories, sugar, sodium, vit_c, vit_a)
                    foodDao.insert(food)
                    line = csvReader.readNext()
                }

            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}

