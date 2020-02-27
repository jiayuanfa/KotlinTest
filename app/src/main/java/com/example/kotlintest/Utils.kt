package com.example.kotlintest

import android.content.res.Resources
import android.util.TypedValue
import android.widget.Toast
import kotlin.jvm.JvmStatic

private val displayMetrics = Resources.getSystem().displayMetrics
fun Float.dp2px(): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, displayMetrics)
}

object Utils {

    @JvmOverloads
    @JvmStatic
    fun toast(string: String?, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(BaseApplication.currentApplication(), string, duration).show()
    }
}