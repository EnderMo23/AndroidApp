package com.sirmo.androidapp.listener

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.text.InputType
import android.view.View
import android.view.animation.TranslateAnimation
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.sirmo.androidapp.MainActivity
import com.sirmo.androidapp.OverAllList
import com.sirmo.androidapp.R
import com.sirmo.androidapp.manager.DataManager

class OnClick {
    companion object {
        val slideInAnimation: TranslateAnimation by lazy {
            val animation = TranslateAnimation(-100f, 0f, 0f, 0f)
            animation.duration = 300
            animation
        }

        val slideOutAnimation: TranslateAnimation by lazy {
            val animation = TranslateAnimation(0f, -100f, 0f, 0f)
            animation.duration = 300
            animation
        }
    }
    fun menuClick(navigationView: NavigationView, imageView: ImageView, context: Context, items: ArrayList<String>, onMenuItemClicked: (itemId: Int) -> Unit) {
        imageView.setOnClickListener {

            if (navigationView.visibility == View.VISIBLE) {
                navigationView.startAnimation(slideOutAnimation)
            }
            else {
                navigationView.startAnimation(slideInAnimation)
            }

            navigationView.visibility = View.VISIBLE

            navigationView.menu.findItem(R.id.action_add)?.isVisible = true
            navigationView.menu.findItem(R.id.action_remove)?.isVisible = true
            navigationView.menu.findItem(R.id.action_listview)?.isVisible = true

            navigationView.setNavigationItemSelectedListener { menuItem ->
                onMenuItemClicked(menuItem.itemId)
                navigationView.visibility = View.INVISIBLE
                true
            }
        }
    }

    fun saveOverAllList(items: ArrayList<String>, context: Context) {
        DataManager.saveData(items, context, "MeinSchlüsselOverAll")
    }

    fun setFabClickListener(fab: FloatingActionButton, items: ArrayList<String>, applicationContextItems: Context, overAllItems: ArrayList<String>, applicationContextItemsOverAll: Context) {
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
                    OverAllList.itemsOverAll.sort()
                }
                DataManager.saveData(items, applicationContextItems, "MeinSchlüssel")
                saveOverAllList(overAllItems, applicationContextItemsOverAll)
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
                DataManager.saveTitle(textView, context)
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

    fun setToDoListClickListener(button: Button, context: Context) {
        button.setOnClickListener {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
            OverAllList.itemAdapter.notifyDataSetChanged()
        }
    }

    fun setOnItemLongClickListener(list: ListView, items: ArrayList<String>, itemAdapter: ArrayAdapter<String>, context: Context, customKey: String) {
        list.onItemLongClickListener = AdapterView.OnItemLongClickListener { _, _, pos, _ ->
            items.removeAt(pos)
            DataManager.saveData(items, context, customKey)
            itemAdapter.notifyDataSetChanged()
            Toast.makeText(context, "Element successfully deleted!", Toast.LENGTH_SHORT)
                .show()
            true
        }
    }

    /*fun setOnItemLongClickListenerOverAll(list: ListView, items: ArrayList<String>, itemAdapter: ArrayAdapter<String>, context: Context) {
        list.onItemLongClickListener = AdapterView.OnItemLongClickListener { _, _, pos, _ ->
            items.removeAt(pos)
            DataManager.saveData(items, context, "MeinSchlüsselOverAll")
            itemAdapter.notifyDataSetChanged()
            Toast.makeText(context, "Element successfully deleted!", Toast.LENGTH_SHORT)
                .show()
            true
        }
    }*/

    fun setOnItemClickListener(list: ListView, context: Context) {
        list.onItemClickListener = AdapterView.OnItemClickListener { _, _, pos, _ ->
            val selectedItemPos = list.getItemAtPosition(pos) as String
            MainActivity.items.add(selectedItemPos)
            MainActivity.itemAdapter.notifyDataSetChanged()
            DataManager.saveData(MainActivity.items, context, "MeinSchlüssel")
        }
    }
}


