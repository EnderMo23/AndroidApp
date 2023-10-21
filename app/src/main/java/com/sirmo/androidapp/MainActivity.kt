package com.sirmo.androidapp

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.graphics.Color
import android.graphics.ColorSpace.Rgb
import android.graphics.Paint
import android.os.Bundle
import android.text.InputType
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.widget.AdapterView.OnItemLongClickListener
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sirmo.androidapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var lvToDoList: ListView
    private lateinit var fab: FloatingActionButton
    //private lateinit var materialToolbar: MaterialToolbar
    //private lateinit var materialToolbar2: MaterialToolbar
    private lateinit var textView: TextView
    private lateinit var textViewHeading: TextView
    private lateinit var items: ArrayList<String>
    private lateinit var itemAdapter: ArrayAdapter<String>
    private lateinit var mTitle: TextView


    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lvToDoList = findViewById(R.id.lvToDoList)
        fab = findViewById(R.id.floatingActionButton2)
        items = ArrayList()
        //materialToolbar = findViewById(R.id.materialToolbar)
        //materialToolbar2 = findViewById(R.id.materialToolbar2)
        textView = findViewById(R.id.textView)
        textViewHeading = findViewById(R.id.textView2)

        itemAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
        lvToDoList.adapter = itemAdapter

        lvToDoList.onItemLongClickListener = OnItemLongClickListener { _, _, pos, _ ->
            items.removeAt(pos)
            itemAdapter.notifyDataSetChanged()
            //Toast.makeText(applicationContext, "Element successfully deleted!", Toast.LENGTH_SHORT).show()

            true
        }
        val colorGreen = Color.parseColor(getString(R.color.green))
        val colorGreenDark = Color.parseColor(getString(R.color.green_dark))
        val colorBlue = Color.parseColor(getString(R.color.blue))

        fab.rippleColor = colorGreen
        //fab.setColorFilter(Color.rgb(39, 158, 255))
        fab.setColorFilter(colorBlue)
        fab.setOnClickListener{

            val builder = AlertDialog.Builder(this)
            builder.setTitle("Add task")
                .setIcon(android.R.drawable.ic_input_add)
            //builder.setTitle

            val inputField = EditText(this)

            inputField.hint = "Type task in here"
            inputField.inputType = InputType.TYPE_CLASS_TEXT
            inputField.setTextColor(colorGreen)
            builder.setView(inputField)

            if (inputField.text.isEmpty()) {
                items.remove(inputField.text.toString())
            }

            builder.setPositiveButton("OK") { _, _ ->
                items.add(inputField.text.toString())
            }

            builder.setNegativeButton("Cancel") { _, _ ->
                //Toast.makeText(applicationContext, "Successfully cancelled!", Toast.LENGTH_SHORT).show()
            }

            builder.show()
        }

        //materialToolbar2.setTitleTextColor(Color.WHITE)

        textView.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        textView.setTextColor(colorGreenDark)

        textViewHeading.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        textViewHeading.setTextColor(colorGreen)


    }
}

