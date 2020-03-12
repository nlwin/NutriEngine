package com.naylwin.eatrition.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food_summary_table")
data class Food(
    @PrimaryKey(autoGenerate=false)
    var foodId: Int = 0,

    @ColumnInfo(name = "food_desc")
    var foodDes: String = "",

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

