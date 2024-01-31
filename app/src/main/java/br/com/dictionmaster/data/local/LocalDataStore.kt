package br.com.dictionmaster.data.local

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LocalDataStore(private val sharedPreferences: SharedPreferences) {

    companion object {
        const val LIST = "LIST"
    }

    fun saveList(list: List<String>) {
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(list)
        editor.putString(LIST, json)
        editor.apply()
    }

    fun getList(): ArrayList<String> {
        val gson = Gson()
        val json = sharedPreferences.getString(LIST, null)
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(json, type)
    }
}

