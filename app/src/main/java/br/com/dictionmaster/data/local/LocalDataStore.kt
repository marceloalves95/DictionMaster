package br.com.dictionmaster.data.local

import android.content.SharedPreferences

class LocalDataStore(private val sharedPreferences: SharedPreferences) {

    companion object {
        const val NUMBER_OF_FOUND_WORDS = "number_of_found_words"
    }

    fun storeNumberOfFoundWords(number: Int) {
        val editor = sharedPreferences.edit()
        editor.putInt(NUMBER_OF_FOUND_WORDS,number)
        editor.apply()
    }

    fun getNumberOfFoundWords(): Int {
        return sharedPreferences.getInt(NUMBER_OF_FOUND_WORDS,0)
    }

}

