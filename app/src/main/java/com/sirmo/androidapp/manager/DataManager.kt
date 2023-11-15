package com.sirmo.androidapp.manager

import android.content.Context
import android.util.Log
import android.widget.TextView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object DataManager {
    fun saveData(items: ArrayList<String>, applicationContext: Context) {
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

    private fun extractTitleText(title: TextView): String {
        return title.text.toString().trim()
    }

    fun saveTitle(title: TextView, applicationContext: Context) {
        val titleText = extractTitleText(title)

        if (titleText.isNotBlank()) {
            Log.i("saveData", "Titel vor dem Speichern: $titleText")

            val sharedPref = applicationContext.getSharedPreferences("MeineEinstellungen", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()

            // Jetzt speicherst du nur den extrahierten Text
            editor.putString("MeinTitel", titleText)

            val success = editor.commit()
            if (success) {
                Log.i("saveData", "Titel erfolgreich gespeichert")
            } else {
                Log.e("saveData", "Fehler beim Speichern des Titels")
            }
        } else {
            Log.w("saveData", "Kein gültiger Titel zum Speichern vorhanden")
        }
    }

    fun loadTitle(titleTextView: TextView, applicationContext: Context) {
        val sharedPref = applicationContext.getSharedPreferences("MeineEinstellungen", Context.MODE_PRIVATE)

        // Den gespeicherten String abrufen
        val json = sharedPref.getString("MeinTitel", null)

        if (json != null) {
            val gson = Gson()

            // Den gespeicherten String in den ursprünglichen Text umwandeln
            val loadedTitle = gson.fromJson(json, String::class.java)

            // Den geladenen Titel in den TextView setzen
            titleTextView.text = loadedTitle

            Log.i("loadData", "Titel erfolgreich geladen: $loadedTitle")
        } else {
            Log.w("loadData", "Kein gespeicherter Titel vorhanden")
        }
    }

    fun saveDataOverAll(overAllItems: ArrayList<String>, applicationContext: Context) {
        if (overAllItems.isNotEmpty()) {
            Log.i("saveData", "Items vor dem Speichern: $overAllItems")
            val sharedPref = applicationContext.getSharedPreferences("MeineEinstellungen", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            val gson = Gson()
            val json = gson.toJson(overAllItems) // Konvertiert die ArrayList in einen JSON-String
            editor.putString("MeinSchlüsselOverAll", json)
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

    fun loadData(context: Context, items: ArrayList<String>) {
        val sharedPref = context.getSharedPreferences("MeineEinstellungen", Context.MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPref.getString("MeinSchlüssel", null)

        // Wenn keine gespeicherte Liste gefunden wurde, initialisieren Sie items als eine leere Liste
        if (json != null) {
            val type = object : TypeToken<ArrayList<String>>() {}.type
            items.clear()
            items.addAll(gson.fromJson(json, type)) // Konvertiert den JSON-String zurück in eine ArrayList
            Log.i("loadData", "Items nach dem Laden: $items")
        } else {
            items.clear()
        }
    }

    fun loadDataOverAll(context: Context, itemsOverAll: ArrayList<String>) {
        val sharedPref = context.getSharedPreferences("MeineEinstellungen", Context.MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPref.getString("MeinSchlüsselOverAll", null)

        // Wenn keine gespeicherte Liste gefunden wurde, initialisieren Sie items als eine leere Liste
        if (json != null) {
            val type = object : TypeToken<ArrayList<String>>() {}.type
            itemsOverAll.clear()
            itemsOverAll.addAll(gson.fromJson(json, type)) // Konvertiert den JSON-String zurück in eine ArrayList
            Log.i("loadData", "Items nach dem Laden: $itemsOverAll")
        } else {
            itemsOverAll.clear()
        }
    }
}