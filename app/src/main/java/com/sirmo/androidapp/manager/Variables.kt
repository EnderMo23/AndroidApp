package com.sirmo.androidapp.manager

import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.sirmo.androidapp.databinding.ActivityMainBinding

class Variables {
    lateinit var binding: ActivityMainBinding
    lateinit var lvToDoList: ListView
    lateinit var fab: FloatingActionButton
    lateinit var textViewHeading: TextView
    lateinit var textViewSubHeading: TextView
    lateinit var imageView: ImageView
    lateinit var buttonEditName: Button
    lateinit var buttonOverallList: Button
    lateinit var navigationView: NavigationView
}