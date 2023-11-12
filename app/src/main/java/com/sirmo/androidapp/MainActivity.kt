package com.sirmo.androidapp

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.widget.AdapterView.OnItemLongClickListener
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sirmo.androidapp.databinding.ActivityMainBinding
import com.sirmo.androidapp.listener.MenuClick
import com.sirmo.androidapp.listener.OnClick
import com.sirmo.androidapp.manager.DataManager


class MainActivity : AppCompatActivity() {

    companion object {
        lateinit var itemAdapter: ArrayAdapter<String>
        lateinit var items: ArrayList<String>
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var lvToDoList: ListView
    private lateinit var fab: FloatingActionButton
    private lateinit var textView: TextView
    private lateinit var textViewHeading: TextView
    private lateinit var imageView: ImageView
    private lateinit var buttonEditName: Button
    private lateinit var buttonOverallList: Button


    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)

            if (requestCode == YOUR_REQUEST_CODE && resultCode == RESULT_OK) {
                val receivedData = data?.getStringExtra("key")
                // Hier können Sie den Wert verwenden, der von SecondActivity gesendet wurde
            }
        }*/

        imageView = findViewById(R.id.imageView2)
        textView = findViewById(R.id.heading)
        textViewHeading = findViewById(R.id.subheading)
        lvToDoList = findViewById(R.id.lvToDoList)
        fab = findViewById(R.id.floatingActionButton2)
        buttonEditName = findViewById(R.id.buttonEditName)
        buttonOverallList = findViewById(R.id.buttonOverallList)
        items = ArrayList()

        DataManager.loadData(this, items)

        val colors = com.sirmo.androidapp.manager.Color(this)

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

        fab.rippleColor = colors.colorGreen
        fab.setColorFilter(colors.colorWhiteLight)

        textView.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        textView.setTextColor(Color.WHITE) //colorGreenDark

        textViewHeading.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        textViewHeading.setTextColor(colors.colorWhiteLight) //colorGreen

        fun test(string: String) {
            items.add(string)
        }

        val onClick = OnClick()

        onClick.menuClick(imageView, items) { itemId ->
            when (itemId) {
                R.id.action_add -> {
                    Toast.makeText(this, "Action was pressed!!!", Toast.LENGTH_SHORT).show()
                    println("Action")
                    MenuClick.setMenuAddClickListener(this, items, textView)
                }

                R.id.action_remove -> {
                    println("Remove")
                    MenuClick.setMenuRemoveClickListener(items)
                    itemAdapter.notifyDataSetChanged()
                }

                R.id.action_listview -> {
                    println("Action List View")
                    DataManager.loadData(this, items)
                    itemAdapter.notifyDataSetChanged()
                }
            }
        }
        onClick.setFabClickListener(fab, items, this, OverAllList.itemsOverAll, this)
        onClick.setEditButtonClickListener(buttonEditName, this, textView)
        onClick.setOverallListButtonClickListener(buttonOverallList, this)
    }
}


