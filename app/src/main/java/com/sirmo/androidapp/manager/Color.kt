package com.sirmo.androidapp.manager

import android.content.Context
import androidx.core.content.ContextCompat
import com.sirmo.androidapp.R

class Color(context: Context){
    val colorGreen = ContextCompat.getColor(context, R.color.green)
    val colorGreenDark = ContextCompat.getColor(context, R.color.green_dark)
    val colorBlue = ContextCompat.getColor(context, R.color.blue)
    val colorWhiteLight = ContextCompat.getColor(context, R.color.white_light)
}