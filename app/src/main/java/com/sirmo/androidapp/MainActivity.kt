package com.sirmo.androidapp

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.widget.AdapterView.OnItemLongClickListener
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sirmo.androidapp.databinding.ActivityMainBinding
import com.sirmo.androidapp.listener.OnClick
import com.sirmo.androidapp.manager.DataManager


class MainActivity : AppCompatActivity() {
    private lateinit var sharedpreferences: SharedPreferences
    private lateinit var binding: ActivityMainBinding
    private lateinit var lvToDoList: ListView
    private lateinit var fab: FloatingActionButton
    private lateinit var textView: TextView
    private lateinit var textViewHeading: TextView
    private lateinit var imageView: ImageView
    private lateinit var items: ArrayList<String>
    private lateinit var itemAdapter: ArrayAdapter<String>
    private lateinit var mTitle: TextView

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        imageView = findViewById(R.id.imageView2)
        textView = findViewById(R.id.textView)
        textViewHeading = findViewById(R.id.textView2)
        lvToDoList = findViewById(R.id.lvToDoList)
        fab = findViewById(R.id.floatingActionButton2)
        items = ArrayList()

        DataManager.loadData(this, items)

        itemAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
        lvToDoList.adapter = itemAdapter

        lvToDoList.onItemLongClickListener = OnItemLongClickListener { _, _, pos, _ ->
            items.removeAt(pos)
            DataManager.saveData(items, this)
            itemAdapter.notifyDataSetChanged()
            Toast.makeText(applicationContext, "Element successfully deleted!", Toast.LENGTH_SHORT)
                .show()

            true
        }

        val colorGreen = Color.parseColor(getString(R.color.green))
        val colorGreenDark = Color.parseColor(getString(R.color.green_dark))
        val colorBlue = Color.parseColor(getString(R.color.blue))
        val colorWhiteLight = Color.parseColor(getString(R.color.white_light))

        fab.rippleColor = colorGreen
        //fab.setColorFilter(Color.rgb(39, 158, 255))
        fab.setColorFilter(colorBlue)

        //materialToolbar2.setTitleTextColor(Color.WHITE)

        textView.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        textView.setTextColor(Color.WHITE) //colorGreenDark

        textViewHeading.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        textViewHeading.setTextColor(colorWhiteLight) //colorGreen

        fun test(string: String) {
            items.add(string)
        }

        val onClick = OnClick(imageView, items) {itemId->
            when (itemId) {
                R.id.action_add -> {
                    Toast.makeText(this, "Action was pressed!!!", Toast.LENGTH_SHORT).show()
                    println("Action")
                }
                R.id.action_remove -> {
                    items.add("Action remove")
                    //DataManager.saveData(items, this)
                    println("Remove")
                    itemAdapter.notifyDataSetChanged()
                }
                R.id.action_listview -> {
                    items.add("ListView")
                    //DataManager.saveData(items, this)
                    println("Action List View")
                    itemAdapter.notifyDataSetChanged()
                }
            }
        }
        onClick.setFabClickListener(fab, items, this)
    }
}


