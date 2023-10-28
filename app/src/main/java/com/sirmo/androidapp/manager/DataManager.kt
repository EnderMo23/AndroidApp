package com.sirmo.androidapp.manager

import android.content.Context
import android.util.Log
import com.google.gson.Gson

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
}