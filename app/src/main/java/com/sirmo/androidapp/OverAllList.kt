package com.sirmo.androidapp

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class OverAllList : AppCompatActivity() {
    companion object {
        var itemsOverAll: ArrayList<String> = ArrayList()
    }

    private lateinit var lvOverAllList: ListView
    private lateinit var itemAdapter: ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_over_all_list)

        lvOverAllList = findViewById(R.id.overAllList)

        /*lvOverAllList = findViewById(R.id.overAllList)
        lvOverAllList.adapter = MainActivity.itemAdapter*/

        itemAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, itemsOverAll)
        lvOverAllList.adapter = itemAdapter

    }
}
