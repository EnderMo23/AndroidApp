package com.sirmo.androidapp.listener

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sirmo.androidapp.OverAllList
import com.sirmo.androidapp.R
import com.sirmo.androidapp.manager.DataManager

class OnClick(private val imageView: ImageView, items: ArrayList<String>, onMenuItemClicked: (itemId: Int) -> Unit) {
    init {
        imageView.setOnClickListener {
            val popupMenu = PopupMenu(it.context, it)
            popupMenu.menuInflater.inflate(R.menu.menu_supermarkets, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { item ->
                onMenuItemClicked(item.itemId)
                true
            }
            popupMenu.show()

            /*val i2 = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=xvFZjo5PgG0"))
            imageView.context.startActivity(i2)*/

        }
    }

    fun setFabClickListener(
        fab: FloatingActionButton,
        items: ArrayList<String>,
        applicationContext: Context,
        overAllItems: ArrayList<String>
    ) {
        fab.setOnClickListener {
            val builder = AlertDialog.Builder(fab.context)
            builder.setTitle("Add task")
                .setIcon(android.R.drawable.ic_input_add)

            val inputField = EditText(fab.context)

            inputField.hint = "Type task in here"
            inputField.inputType = InputType.TYPE_CLASS_TEXT
            inputField.setTextColor(Color.GREEN)
            builder.setView(inputField)

            if (inputField.text.isEmpty()) {
                items.remove(inputField.text.toString())
            }

            builder.setPositiveButton("OK") { _, _ ->
                if (!items.contains(inputField.text.toString())) {
                    items.add(inputField.text.toString())
                }
                if (!overAllItems.contains(inputField.text.toString())) {
                    overAllItems.add(inputField.text.toString())
                }
                DataManager.saveData(items, applicationContext)
                // saveData() // Sie müssen diese Funktion definieren
                //Toast.makeText(fab.context, "Task successfully added with the name ${inputField.text}" + "!", Toast.LENGTH_SHORT).show()
            }

            builder.setNegativeButton("Cancel") { _, _ ->
                //Toast.makeText(fab.context, "Successfully cancelled!", Toast.LENGTH_SHORT).show()
            }
            builder.show()
        }
    }

    fun setEditButtonClickListener(button: Button, context: Context, textView: TextView) {
        button.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Add task")
                .setIcon(android.R.drawable.ic_input_add)

            val inputField = EditText(context)

            inputField.hint = "Type list name in here"
            inputField.inputType = InputType.TYPE_CLASS_TEXT
            builder.setView(inputField)

            builder.setPositiveButton("OK") { _, _ ->
                textView.text = inputField.text.toString()
                // saveData() // Sie müssen diese Funktion definieren
                //Toast.makeText(fab.context, "Task successfully added with the name ${inputField.text}" + "!", Toast.LENGTH_SHORT).show()
            }

            builder.setNegativeButton("Cancel") { _, _ ->
                //Toast.makeText(fab.context, "Successfully cancelled!", Toast.LENGTH_SHORT).show()
            }
            builder.show()
        }
    }

    fun setOverallListButtonClickListener(button: Button, context: Context) {
        button.setOnClickListener {
            val intent = Intent(context, OverAllList::class.java)
            context.startActivity(intent)

        }
    }
}


