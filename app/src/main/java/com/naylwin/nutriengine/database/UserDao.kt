package com.naylwin.eatrition.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {
    @Insert
    fun insert(user: User)

    @Update
    fun update(user: User)

    @Query("SELECT * FROM user_table WHERE user_name LIKE :urname")
    fun getUser(urname: String): User?

    @Query("SELECT * FROM user_table")
    fun getAllUsers(): List<User?>

    @Query("DELETE FROM user_table WHERE user_name=:urname")
    fun deleteUser(urname: String)
}



