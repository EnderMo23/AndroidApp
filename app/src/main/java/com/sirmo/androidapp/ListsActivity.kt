package com.sirmo.androidapp

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.sirmo.androidapp.listener.OnClick
import com.sirmo.androidapp.manager.DataManager
import com.sirmo.androidapp.manager.Variables

class ListsActivity : AppCompatActivity() {
    companion object {
        lateinit var items: ArrayList<String>
        lateinit var itemAdapter: ArrayAdapter<String>
    }

    private lateinit var listView: ListView
    private lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lists)

        val variables = Variables()
        listView = findViewById(R.id.listView)
        items = ArrayList()
        itemAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
        textView = findViewById(R.id.textView)
        listView.adapter = itemAdapter

        DataManager.loadData(this, items, "MeinSchlüsselLists")

        val inputTest = intent.getStringExtra("inputText")
        val onClick = OnClick()

        DataManager.loadTitle(textView, this)
        //DataManager.loadData(this, items, "MeinSchlüssel")

        items.add(inputTest.toString())
        items.removeAll { it == "null" }
        DataManager.saveData(items, this, "MeinSchlüsselLists")

        onClick.setOnItemClickListenerLists(listView, this)

        onClick.setOnItemLongClickListener(listView, items, itemAdapter, this, "MeinSchlüsselLists")
    }
}