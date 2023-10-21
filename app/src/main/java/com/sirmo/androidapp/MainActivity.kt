package com.sirmo.androidapp

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.ColorSpace.Rgb
import android.graphics.Paint
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.widget.AdapterView.OnItemLongClickListener
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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

        fab.rippleColor = Color.GREEN
        fab.setBackgroundColor(Color.RED)
        fab.setColorFilter(Color.RED)
        fab.setOnClickListener{


            var builder = AlertDialog.Builder(this)
            builder.setTitle("Add")

            var inputField = EditText(this)

            inputField.hint = "Type task in here"
            inputField.inputType = InputType.TYPE_CLASS_TEXT
            builder.setView(inputField)

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
        textView.setTextColor(Color.rgb(63,81,181))

        textViewHeading.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        textViewHeading.setTextColor(Color.rgb(63,81,181))


    }
}

