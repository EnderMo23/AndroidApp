package com.sirmo.androidapp

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.sirmo.androidapp.listener.OnClick
import com.sirmo.androidapp.manager.DataManager

class OverAllList : AppCompatActivity() {
    companion object {
        var itemsOverAll: ArrayList<String> = ArrayList()
        var applicationContextOverAll = this
        lateinit var itemAdapter: ArrayAdapter<String>
    }

    private lateinit var lvOverAllList: ListView
    private lateinit var toToDoListButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_over_all_list)

        val onClick = OnClick()

        lvOverAllList = findViewById(R.id.overAllList)
        toToDoListButton = findViewById(R.id.toToDoListButton)

        DataManager.loadData(this, itemsOverAll, "MeinSchlüsselOverAll")

        itemsOverAll.sort()

        itemAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, itemsOverAll)
        lvOverAllList.adapter = itemAdapter

        onClick.setOnItemClickListener(lvOverAllList, this)

        onClick.setOnItemLongClickListener(lvOverAllList, itemsOverAll, itemAdapter, this, "MeinSchlüsselOverAll")


        onClick.setToDoListClickListener(toToDoListButton, this)

    }
}
