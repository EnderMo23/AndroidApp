package com.sirmo.androidapp

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class OverAllList : AppCompatActivity() {

    private lateinit var lvOverAllList: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_over_all_list)

        lvOverAllList = findViewById(R.id.overAllList)
        lvOverAllList.adapter = MainActivity.itemAdapter

    }
}
