package com.sirmo.androidapp.manager

import android.content.Context
import androidx.core.content.ContextCompat
import com.sirmo.androidapp.R

class Color(context: Context){
    val colorGreen = ContextCompat.getColor(context, R.color.green)
    val colorGreenDark = ContextCompat.getColor(context, R.color.green_dark)
    val colorBlue = ContextCompat.getColor(context, R.color.blue)
    val colorWhiteLight = ContextCompat.getColor(context, R.color.white_light)

    /*private lateinit var fab: FloatingActionButton
    private lateinit var textViewHeading: TextView
    private lateinit var textViewSubHeading: TextView
    private lateinit var imageView: ImageView
    private lateinit var buttonEditName: Button
    private lateinit var buttonOverallList: Button
    private lateinit var navigationView: NavigationView
    private lateinit var lvToDoList: ListView*/
}