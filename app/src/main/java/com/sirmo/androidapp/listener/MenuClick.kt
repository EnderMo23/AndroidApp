package com.sirmo.androidapp.listener

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.text.InputType
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.navigation.NavigationView
import com.sirmo.androidapp.ListsActivity
import com.sirmo.androidapp.MainActivity
import com.sirmo.androidapp.R
import com.sirmo.androidapp.manager.DataManager
import com.sirmo.androidapp.manager.ListManager

class MenuClick {
    companion object {
        var i = 0
        val randomKey = DataManager.createKey(5)
        private fun createNewList(items: ArrayList<String>) {
            items.clear()
            //DataManager.saveData(items, context)
        }
        private fun setMenuAddClickListener(context: Context, items: ArrayList<String>, textView: TextView) {
            println("Add was pressed!!!!!!!!!!")
            //saveData
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Add new List")
                .setIcon(android.R.drawable.ic_input_add)

            val inputField = EditText(context)
            inputField.hint = "New task name"
            inputField.inputType = InputType.TYPE_CLASS_TEXT
            inputField.setTextColor((android.graphics.Color.GREEN))
            builder.setView(inputField)

            builder.setPositiveButton("OK") { _, _ ->
                if (inputField.text.isEmpty()) {
                    return@setPositiveButton
                }
                println("New list was created with the name ${inputField.text}!!!")
                //items.add(inputField.text.toString())
                createNewList(items)
                textView.text = inputField.text.toString()

                if (inputField.text.toString().isNotEmpty()) {
                    //ListsActivity.items.add(inputField.text.toString())
                    MainActivity.items.add(inputField.text.toString())
                    val intent = Intent(context, ListsActivity::class.java)
                    intent.putExtra("inputText", inputField.text.toString())
                    //random
                    //var saveList = DataManager.saveData(items, context, randomKey).toString() //${i} "MeinSchlüssel"
                    ListManager.lists.add(items.toString())
                    println(ListManager.lists)
                    //i ++
                    context.startActivity(intent)
                }

                //DataManager.saveData(items, context)
            }

            builder.setNegativeButton("Cancel") { _, _ ->
                //Toast.makeText(fab.context, "Successfully cancelled!", Toast.LENGTH_SHORT).show()
            }
            builder.show()
        }

        private fun setMenuRemoveClickListener(items: ArrayList<String>) {
            items.clear()
        }

        fun menuClickItemId(navigationView: NavigationView, imageView: ImageView, items: ArrayList<String>, textView: TextView, context: Context) {
            val onClick = OnClick()
            onClick.menuClick(navigationView, imageView, context, items) { itemId ->
                when (itemId) {
                    R.id.action_add -> {
                        Toast.makeText(context, "Action was pressed!!!", Toast.LENGTH_SHORT).show()
                        println("Action")
                        navigationView.startAnimation(OnClick.slideOutAnimation)
                        setMenuAddClickListener(context, MainActivity.items, textView)
                    }

                    R.id.action_remove -> {
                        println("Remove")
                        setMenuRemoveClickListener(MainActivity.items)
                        navigationView.startAnimation(OnClick.slideOutAnimation)
                        MainActivity.itemAdapter.notifyDataSetChanged()
                    }

                    R.id.action_listview -> {
                        println("List View")
                        DataManager.loadData(context, MainActivity.items, "MeinSchlüssel")
                        navigationView.startAnimation(OnClick.slideOutAnimation)
                        MainActivity.itemAdapter.notifyDataSetChanged()
                        val intent = Intent(context, ListsActivity::class.java)
                        context.startActivity(intent)

                    }

                    R.id.action_back -> {
                        println("Back")
                        navigationView.startAnimation(OnClick.slideOutAnimation)
                        navigationView.visibility = View.INVISIBLE
                    }
                }
            }
        }
    }
}