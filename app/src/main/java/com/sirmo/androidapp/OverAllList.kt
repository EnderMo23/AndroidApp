package com.sirmo.androidapp

import android.os.Bundle
import android.widget.AdapterView
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

        lvOverAllList = findViewById(R.id.overAllList)
        toToDoListButton = findViewById(R.id.toToDoListButton)

        DataManager.loadDataOverAll(this, itemsOverAll)

        itemsOverAll.sort()

        /*lvOverAllList = findViewById(R.id.overAllList)
        lvOverAllList.adapter = MainActivity.itemAdapter*/

        itemAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, itemsOverAll)
        lvOverAllList.adapter = itemAdapter

        lvOverAllList.onItemClickListener = AdapterView.OnItemClickListener { _, _, pos, _ ->
            val selectedItemPos = lvOverAllList.getItemAtPosition(pos) as String
            MainActivity.items.add(selectedItemPos)
            MainActivity.itemAdapter.notifyDataSetChanged()
            DataManager.saveData(MainActivity.items, this)
        }

        lvOverAllList.onItemLongClickListener = AdapterView.OnItemLongClickListener { _, _, pos, _ ->
            itemsOverAll.removeAt(pos)
            itemAdapter.notifyDataSetChanged()
            DataManager.saveDataOverAll(itemsOverAll, this)
            true
        }

        val onClick = OnClick()
        onClick.setToDoListClickListener(toToDoListButton, this)

    }
}
