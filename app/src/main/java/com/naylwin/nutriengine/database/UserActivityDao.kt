package com.naylwin.eatrition.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserActivityDao {

    @Insert
    fun insert(user: UserActivity)

    @Update
    fun update(user: UserActivity)

    @Query("SELECT * FROM activity_table WHERE user_name=:username AND date=:date")
    fun getUserActivity(username: String, date: String): UserActivity?

    @Query("SELECT * FROM activity_table WHERE user_name=:username AND date=:date")
    fun getLiveUserActivity(username: String, date: String): LiveData<UserActivity?>

    @Query("SELECT * FROM activity_table WHERE user_name = :username")
    fun getUserActivities(username: String) : List<UserActivity?>

}