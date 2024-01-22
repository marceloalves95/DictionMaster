package br.com.dictionmaster.domain.models

data class Search(
    val word: String?,
    val phonetic: String?,
    val phonetics: List<Phonetics>?,
    val origin: String?,
    val meanings: List<Meanings>?,
    val license: License?,
    val sourceUrls: List<String>?
)

data class Phonetics(
    val text: String?,
    val audio: String?,
    val sourceUrl: String?,
    val license: License?
)

data class License(
    val name: String?,
    val url: String?
)

data class Meanings(
    val partOfSpeech: String?,
    val definitions: List<Definitions>?,
    val synonyms: List<String>?,
    val antonyms: List<String>?
)

data class Definitions(
    val definition: String?,
    val synonyms: List<String>?,
    val antonyms: List<String>?,
    val example: String?
)