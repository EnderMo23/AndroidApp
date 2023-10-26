package com.sirmo.androidapp

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.ColorSpace.Rgb
import android.graphics.Paint
import android.net.Uri
import android.os.Bundle
import android.text.InputType
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.widget.AdapterView.OnItemLongClickListener
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sirmo.androidapp.databinding.ActivityMainBinding
import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.widget.SearchView.OnCloseListener
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlin.math.log


class MainActivity : AppCompatActivity() {
    private lateinit var sharedpreferences: SharedPreferences
    private lateinit var binding: ActivityMainBinding
    private lateinit var lvToDoList: ListView
    private lateinit var fab: FloatingActionButton

    //private lateinit var materialToolbar: MaterialToolbar
    //private lateinit var materialToolbar2: MaterialToolbar
    private lateinit var textView: TextView
    private lateinit var textViewHeading: TextView
    private lateinit var imageView: ImageView
    private lateinit var items: ArrayList<String>
    private lateinit var itemAdapter: ArrayAdapter<String>
    private lateinit var mTitle: TextView


    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        imageView = findViewById(R.id.imageView2)
        textView = findViewById(R.id.textView)
        textViewHeading = findViewById(R.id.textView2)
        lvToDoList = findViewById(R.id.lvToDoList)
        fab = findViewById(R.id.floatingActionButton2)
        items = ArrayList()

        //materialToolbar = findViewById(R.id.materialToolbar)
        //materialToolbar2 = findViewById(R.id.materialToolbar2)

        /*fun loadData(context: Context) {
            val sharedPref = context.getSharedPreferences("MeineEinstellungen", Context.MODE_PRIVATE)
            val gson = Gson()
            val json = sharedPref.getString("MeinSchlüssel", null)
            Log.i("loadData", "Geladener JSON-String: $json")


            // Wenn keine gespeicherte Liste gefunden wurde, initialisieren Sie items als eine leere Liste
            if (json != null) {
                val type = object : TypeToken<ArrayList<String>>() {}.type
                items = gson.fromJson(json, type) // Konvertiert den JSON-String zurück in eine ArrayList
                Log.i("loadData", "Geladene Daten: $items")

            } else {
                items = ArrayList()
            }
        }

        fun saveData() {
            if (items.isNotEmpty()) {
                val sharedPref = applicationContext.getSharedPreferences("MeineEinstellungen", Context.MODE_PRIVATE)
                val editor = sharedPref.edit()
                val gson = Gson()
                Log.i("saveData", "Speichere Daten: $items")
                val json = gson.toJson(items) // Konvertiert die ArrayList in einen JSON-String
                Log.i("saveData", "Gespeicherter JSON-String: $json")
                editor.putString("MeinSchlüssel", json)
                val success = editor.commit() // Verwendet commit() anstelle von apply(), um den Erfolg des Speicherns zu überprüfen
                if (success) {
                    Log.i("saveData", "Daten erfolgreich gespeichert")
                } else {
                    Log.e("saveData", "Fehler beim Speichern der Daten")
                }
            } else {
                Log.w("saveData", "Keine Daten zum Speichern vorhanden")
            }
        }*/

        fun saveData() {
            if (items.isNotEmpty()) {
                Log.i("saveData", "Items vor dem Speichern: $items")
                val sharedPref = applicationContext.getSharedPreferences("MeineEinstellungen", Context.MODE_PRIVATE)
                val editor = sharedPref.edit()
                val gson = Gson()
                val json = gson.toJson(items) // Konvertiert die ArrayList in einen JSON-String
                editor.putString("MeinSchlüssel", json)
                val success = editor.commit() // Verwendet commit() anstelle von apply(), um den Erfolg des Speicherns zu überprüfen
                if (success) {
                    Log.i("saveData", "Daten erfolgreich gespeichert")
                } else {
                    Log.e("saveData", "Fehler beim Speichern der Daten")
                }
            } else {
                Log.w("saveData", "Keine Daten zum Speichern vorhanden")
            }
        }

        fun loadData(context: Context) {
            val sharedPref = context.getSharedPreferences("MeineEinstellungen", Context.MODE_PRIVATE)
            val gson = Gson()
            val json = sharedPref.getString("MeinSchlüssel", null)

            // Wenn keine gespeicherte Liste gefunden wurde, initialisieren Sie items als eine leere Liste
            if (json != null) {
                val type = object : TypeToken<ArrayList<String>>() {}.type
                items = gson.fromJson(json, type) // Konvertiert den JSON-String zurück in eine ArrayList
                Log.i("loadData", "Items nach dem Laden: $items")
            } else {
                items = ArrayList()
            }
        }


        fun clearData() {
            val sharedPref = applicationContext.getSharedPreferences("MeineEinstellungen", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.clear()
            editor.apply()
            println("Test")
        }

        //clearData()
        loadData(this)



        /*fun loadData() {
            val sharedPref = applicationContext.getSharedPreferences("MeineEinstellungen", Context.MODE_PRIVATE)
            val gson = Gson()
            val editor = sharedPref.edit()
            val json = sharedPref.getString("MeinSchlüssel", null)
            editor.putString("MeinSchlüssel", "Ihr Wert")
            editor.apply()

            // Wenn keine gespeicherte Liste gefunden wurde, initialisieren Sie items als eine leere Liste
            if (json != null) {
                val type = object : TypeToken<ArrayList<String>>() {}.type
                items = gson.fromJson(json, type) // Konvertiert den JSON-String zurück in eine ArrayList
            } else {
                items = ArrayList()
            }
        }*/



        itemAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
        lvToDoList.adapter = itemAdapter

        lvToDoList.onItemLongClickListener = OnItemLongClickListener { _, _, pos, _ ->
            items.removeAt(pos)
            itemAdapter.notifyDataSetChanged()
            Toast.makeText(applicationContext, "Element successfully deleted!", Toast.LENGTH_SHORT)
                .show()

            true
        }
        val colorGreen = Color.parseColor(getString(R.color.green))
        val colorGreenDark = Color.parseColor(getString(R.color.green_dark))
        val colorBlue = Color.parseColor(getString(R.color.blue))
        val colorWhiteLight = Color.parseColor(getString(R.color.white_light))

        //imageView.setImageResource(R.drawable.ic_launcher_background)
        imageView.setOnClickListener {
            val i2 =
                Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=xvFZjo5PgG0"))
            startActivity(i2)
        }

        fab.rippleColor = colorGreen
        //fab.setColorFilter(Color.rgb(39, 158, 255))
        fab.setColorFilter(colorBlue)
        fab.setOnClickListener {

            val builder = AlertDialog.Builder(this)
            builder.setTitle("Add task")
                .setIcon(android.R.drawable.ic_input_add)
            //builder.setTitle

            val inputField = EditText(this)

            inputField.hint = "Type task in here"
            inputField.inputType = InputType.TYPE_CLASS_TEXT
            inputField.setTextColor(colorGreen)
            builder.setView(inputField)

            if (inputField.text.isEmpty()) {
                items.remove(inputField.text.toString())
                //Toast.makeText(applicationContext, "Please enter a task!", Toast.LENGTH_SHORT).show()
            }

            builder.setPositiveButton("OK") { _, _ ->
                items.add(inputField.text.toString())
                saveData()
                //Toast.makeText(applicationContext, "Task successfully added with the name ${inputField.text}" + "!", Toast.LENGTH_SHORT).show()
            }

            builder.setNegativeButton("Cancel") { _, _ ->
                Toast.makeText(applicationContext, "Successfully cancelled!", Toast.LENGTH_SHORT)
                    .show()
            }

            builder.show()
        }

        //materialToolbar2.setTitleTextColor(Color.WHITE)

        textView.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        textView.setTextColor(Color.WHITE) //colorGreenDark

        textViewHeading.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        textViewHeading.setTextColor(colorWhiteLight) //colorGreen

        //Saving System
        /*val sharedPref = getSharedPreferences(getString(R.string.com_sirmo_androidapp), Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putInt(getString(R.string.com_sirmo_androidapp), colorGreen)
            apply()
        }
        val shared = getPreferences(Context.MODE_PRIVATE) ?: return*/

        //Alternative Saving System
        /*class User(var username: String, sharedPref: SharedPreferences) {
            val sharedPreff : SharedPreferences = applicationContext.getSharedPreferences("com.sirmo.androidapp", MODE_PRIVATE)
            val user = User("sirmo", sharedPref)
            user.Save()
        }*/

        /*class User (var username : String) {
            private val PREFERENCE_FILE_KEY = "myAppPreference"
            private val USERNAME_KEY = "username"

            fun saveUsername(context: Context) {
                val sharedPref = context.getSharedPreferences(PREFERENCE_FILE_KEY, MODE_PRIVATE)
                with (sharedPref.edit()) {
                    putString(USERNAME_KEY, username)
                    apply()
                }
            }
        }

        val user = User("SirMo_")
        user.saveUsername(this)*/

        /*val gson = Gson()
        val json = gson.toJson(items) // Konvertiert die ArrayList in einen JSON-String

        val sharedPref = applicationContext.getSharedPreferences("MeineEinstellungen", Context.MODE_PRIVATE)
        with (sharedPref.edit()) {
            putString("MeinSchlüssel", json)
            apply()
        }

        //val sharedPref = applicationContext.getSharedPreferences("MeineEinstellungen", Context.MODE_PRIVATE)
        val jsonRetrieve = sharedPref.getString("MeinSchlüssel", null)

        val type = object : TypeToken<ArrayList<String>>() {}.type
        val arrayList: ArrayList<String> = gson.fromJson(jsonRetrieve, type) // Konvertiert den JSON-String zurück in eine ArrayList
*/
    }
}


