package com.naylwin.eatrition.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User (
    @PrimaryKey(autoGenerate=true)
    var userId: Int = 0,

    @ColumnInfo(name = "user_name")
    var name: String = "",

    @ColumnInfo(name = "age")
    var age: Int = 0,

    @ColumnInfo(name="height")
    var height: Int = 0,

    @ColumnInfo(name="weight")
    var weight: Int =0
)