package com.naylwin.nutriengine

import android.os.Build
import android.text.Html
import android.text.Spanned
import androidx.core.text.HtmlCompat
import com.naylwin.eatrition.database.Food

fun formatFood(foodList: List<Food?>): Spanned {
    val sb = StringBuilder()
    sb.apply {
        append("Choose Your Possible Food")
        append("<br>")
        foodList.forEach {
            append("<b>FoodID</b> - " + it?.foodId)
            append("<br>")
            append("<b>Calories</b> - " + it?.calories)
            append("<br>")
            append("<b>Description</b> - " + it?.foodDes)
            append("<br>")
            append("<b>Sugar</b> - " + it?.sugar )
            append("<br>")
            append("<b>Sodium</b> - " + it?.sodium)
            append("<br>")
            append("<b>VitaminA</b> - " + it?.vit_a)
            append("<br>")
            append("<b>VitaminC</b> - " + it?.vit_c)
            append("<br>")
            append("<br>")
        }
    }

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        return Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_LEGACY)
    } else {
        return HtmlCompat.fromHtml(sb.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
    }
}

