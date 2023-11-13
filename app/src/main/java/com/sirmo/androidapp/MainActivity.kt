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
import com.google.android.material.navigation.NavigationView
import com.sirmo.androidapp.databinding.ActivityMainBinding
import com.sirmo.androidapp.listener.MenuClick
import com.sirmo.androidapp.listener.OnClick
import com.sirmo.androidapp.manager.DataManager


class MainActivity : AppCompatActivity() {

    companion object {
        lateinit var itemAdapter: ArrayAdapter<String>
        lateinit var items: ArrayList<String>
        /*@SuppressLint("StaticFieldLeak")
        lateinit var rootLayout: ViewGroup
        lateinit var drawerLayout: DrawerLayout*/
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var lvToDoList: ListView
    private lateinit var fab: FloatingActionButton
    private lateinit var textViewHeading: TextView
    private lateinit var textViewSubHeading: TextView
    private lateinit var imageView: ImageView
    private lateinit var buttonEditName: Button
    private lateinit var buttonOverallList: Button
    private lateinit var navigationView: NavigationView

    @SuppressLint("ResourceType", "ClickableViewAccessibility")
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
        textViewHeading = findViewById(R.id.heading)
        textViewSubHeading = findViewById(R.id.subheading)
        lvToDoList = findViewById(R.id.lvToDoList)
        fab = findViewById(R.id.floatingActionButton2)
        buttonEditName = findViewById(R.id.buttonEditName)
        buttonOverallList = findViewById(R.id.buttonOverallList)
        navigationView = findViewById(R.id.navView)
        items = ArrayList()
        //rootLayout = findViewById(android.R.id.content)

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

        textViewHeading.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        textViewHeading.setTextColor(Color.WHITE) //colorGreenDark

        textViewSubHeading.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        textViewSubHeading.setTextColor(colors.colorWhiteLight) //colorGreen

        fun test(string: String) {
            items.add(string)
        }

        val onClick = OnClick()

        MenuClick.menuClickItemId(navigationView, imageView, items, textViewHeading, this)

        onClick.setFabClickListener(fab, items, this, OverAllList.itemsOverAll, this)
        onClick.setEditButtonClickListener(buttonEditName, this, textViewHeading)
        onClick.setOverallListButtonClickListener(buttonOverallList, this)
    }
}



