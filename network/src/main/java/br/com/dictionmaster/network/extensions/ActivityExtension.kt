package br.com.dictionmaster.network.extensions

import android.app.Activity
import androidx.activity.ComponentActivity

inline fun <reified T> Activity.extra(key: String): Lazy<T> = lazy {
    val value = intent.extras?.get(key)
    if (value is T) value else throw IllegalArgumentException("didn't find extra with key $key")
}

fun ComponentActivity.onBackButtonPressed() {
    onBackPressedDispatcher.onBackPressed()
}