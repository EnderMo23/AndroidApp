package com.sirmo.androidapp

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.sirmo.androidapp.databinding.ActivityMainBinding
import com.sirmo.androidapp.listener.MenuClick
import com.sirmo.androidapp.listener.OnClick
import com.sirmo.androidapp.manager.DataManager
import com.sirmo.androidapp.manager.Variables


class MainActivity : AppCompatActivity() {

    companion object {
        lateinit var itemAdapter: ArrayAdapter<String>
        lateinit var items: ArrayList<String>
    }

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("ResourceType", "ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val variables = Variables()
        val colors = com.sirmo.androidapp.manager.Color(this)
        val onClick = OnClick()

        variables.imageView = findViewById(R.id.imageView2)
        variables.textViewHeading = findViewById(R.id.heading)
        variables.textViewSubHeading = findViewById(R.id.subheading)
        variables.lvToDoList = findViewById(R.id.lvToDoList)
        variables.fab = findViewById(R.id.floatingActionButton2)
        variables.buttonEditName = findViewById(R.id.buttonEditName)
        variables.buttonOverallList = findViewById(R.id.buttonOverallList)
        variables.navigationView = findViewById(R.id.navView)
        items = ArrayList()

        DataManager.loadData(this, items)

        itemAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
        variables.lvToDoList.adapter = itemAdapter

        onClick.setOnItemLongClickListenerToDo(variables.lvToDoList, items, itemAdapter,this)

        variables.fab.rippleColor = colors.colorGreen
        variables.fab.setColorFilter(colors.colorWhiteLight)

        variables.textViewHeading.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        variables.textViewHeading.setTextColor(Color.WHITE) //colorGreenDark

        variables.textViewSubHeading.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        variables.textViewSubHeading.setTextColor(colors.colorWhiteLight) //colorGreen

        fun test(string: String) {
            items.add(string)
        }

        MenuClick.menuClickItemId(variables.navigationView, variables.imageView, items, variables.textViewHeading, this)

        onClick.setFabClickListener(variables.fab, items, this, OverAllList.itemsOverAll, this)
        onClick.setEditButtonClickListener(variables.buttonEditName, this, variables.textViewHeading)
        onClick.setOverallListButtonClickListener(variables.buttonOverallList, this)
    }
}