package com.sirmo.androidapp.listener

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.text.InputType
import android.widget.EditText
import com.sirmo.androidapp.manager.DataManager

class MenuClick {
    companion object {
        fun createNewList(items: ArrayList<String>, context: Context) {
            val oldList = items
            items.clear()
            DataManager.saveData(items, context)
        }
        fun setMenuAddClickListener(context: Context, items: ArrayList<String>) {
            println("Add was pressed!!!!!!!!!!")
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Add new List")
                .setIcon(android.R.drawable.ic_input_add)

            val inputField = EditText(context)
            inputField.hint = "New task name"
            inputField.inputType = InputType.TYPE_CLASS_TEXT
            inputField.setTextColor((Color.GREEN))
            builder.setView(inputField)

            builder.setPositiveButton("OK") { _, _ ->
                if (inputField.text.isEmpty()) {
                    return@setPositiveButton
                }
                println("New list was created with the name ${inputField.text}!!!")
                //items.add(inputField.text.toString())
                createNewList(items, context)
            }

            builder.setNegativeButton("Cancel") { _, _ ->
                //Toast.makeText(fab.context, "Successfully cancelled!", Toast.LENGTH_SHORT).show()
            }
            builder.show()
        }

        fun setMenuRemoveClickListener(items: ArrayList<String>) {
            items.clear()
        }
    }
}