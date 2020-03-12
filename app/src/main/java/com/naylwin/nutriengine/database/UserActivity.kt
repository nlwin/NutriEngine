package com.naylwin.eatrition.database

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["user_name","date"], tableName = "activity_table")
data class UserActivity(
    var user_name: String,

    var date: String,

    @ColumnInfo(name = "calories")
    var calories: Int = 0,

    @ColumnInfo(name = "sugar")
    var sugar: Float = 0F,

    @ColumnInfo(name = "sodium")
    var sodium: Int = 0,

    @ColumnInfo(name = "vit_c")
    var vit_c: Float = 0F,

    @ColumnInfo(name = "vit_a")
    var vit_a: Int = 0
)