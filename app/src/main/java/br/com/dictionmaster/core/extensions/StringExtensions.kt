package br.com.dictionmaster.core.extensions

import java.util.Locale

fun String.capitalizeWord(): String {
    val locale = Locale("pt", "BR")
    return this.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(locale) else it.toString()
    }
}