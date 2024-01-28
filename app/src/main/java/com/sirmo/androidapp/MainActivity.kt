package com.sirmo.androidapp

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
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
    private lateinit var rootView: ViewGroup
    private lateinit var fab: FloatingActionButton
    private lateinit var textViewHeading: TextView
    private lateinit var textViewSubHeading: TextView
    private lateinit var imageView: ImageView
    private lateinit var buttonEditName: Button
    private lateinit var buttonOverallList: Button
    private lateinit var navigationView: NavigationView
    private lateinit var lvToDoList: ListView
    private lateinit var clearButton: Button

    @SuppressLint("ResourceType", "ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val variables = Variables()
        val colors = com.sirmo.androidapp.manager.Color(this)
        val onClick = OnClick()

        rootView = findViewById(android.R.id.content)

        imageView = findViewById(R.id.imageView2)
        textViewHeading = findViewById(R.id.heading)
        textViewSubHeading = findViewById(R.id.subheading)
        lvToDoList = findViewById(R.id.lvToDoList)
        fab = findViewById(R.id.floatingActionButton2)
        buttonEditName = findViewById(R.id.buttonEditName)
        buttonOverallList = findViewById(R.id.buttonOverallList)
        navigationView = findViewById(R.id.navView)
        items = ArrayList()
        clearButton = findViewById(R.id.clearButton)

        DataManager.loadData(this, items, "MeinSchlüssel")
        DataManager.loadTitle(textViewHeading, this)
        navigationView.visibility = View.INVISIBLE

        itemAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
        lvToDoList.adapter = itemAdapter

        onClick.setOnItemLongClickListener(lvToDoList, items, itemAdapter,this, "MeinSchlüssel")

        fab.rippleColor = colors.colorGreen
        fab.setColorFilter(colors.colorWhiteLight)

        textViewHeading.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        textViewHeading.setTextColor(Color.WHITE)

        textViewSubHeading.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        textViewSubHeading.setTextColor(colors.colorWhiteLight)

        fun test(string: String) {
            items.add(string)
        }

        test("asd")

        /*fun isTouchInsideView(event: MotionEvent, view: View): Boolean {
            val location = IntArray(2)
            view.getLocationOnScreen(location)
            val viewRect = Rect(
                location[0],
                location[1],
                location[0] + view.width,
                location[1] + view.height
            )

            val touchPoint = Pair(event.rawX.toInt(), event.rawY.toInt())

            println("Touch!!!!")
            return viewRect.contains(touchPoint.first, touchPoint.second)
        }

        variables.navigationView.setOnTouchListener { _, event ->
            println("onTouchListener called")
            if (event.action == MotionEvent.ACTION_DOWN && !isTouchInsideView(event, variables.navigationView)) {
                variables.navigationView.visibility = View.INVISIBLE
            }

            return@setOnTouchListener false
        }*/

        MenuClick.menuClickItemId(navigationView, imageView, items, textViewHeading, this)

        onClick.setFabClickListener(fab, items, this, OverAllList.itemsOverAll, this)
        onClick.setEditButtonClickListener(buttonEditName, this, textViewHeading)
        onClick.setOverallListButtonClickListener(buttonOverallList, this)
        onClick.setClearClickListener(clearButton, items, this)
    }
}