package com.naylwin.eatrition.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface FoodDao {

    @Insert
    fun insert(food: Food)

    @Update
    fun update(food: Food)

    @Query("SELECT * FROM food_summary_table WHERE foodId = :key")
    fun getFoodStats(key: Int): Food?

    @Query("SELECT * FROM food_summary_table WHERE food_desc LIKE :foodStr LIMIT 10")
    fun findFoods(foodStr: String): LiveData<List<Food?>>

    @Query("SELECT * FROM food_summary_table LIMIT 1")
    fun findFirstFood(): Food?

}

