package br.com.dictionmaster.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchResponse(
    @SerializedName("word")
    val word: String?,
    @SerializedName("phonetic")
    val phonetic: String?,
    @SerializedName("phonetics")
    val phonetics: List<PhoneticsResponse>?,
    @SerializedName("origin")
    val origin: String?,
    @SerializedName("meanings")
    val meanings: List<MeaningsResponse>?,
    @SerializedName("license")
    val license: LicenseResponse?,
    @SerializedName("sourceUrls")
    val sourceUrls: List<String>?
) : Parcelable

@Parcelize
data class PhoneticsResponse(
    @SerializedName("text")
    val text: String?,
    @SerializedName("audio")
    val audio: String?,
    @SerializedName("sourceUrl")
    val sourceUrl: String?,
    @SerializedName("license")
    val license: LicenseResponse?
) : Parcelable

@Parcelize
data class LicenseResponse(
    @SerializedName("name")
    val name: String?,
    @SerializedName("url")
    val url: String?
) : Parcelable

@Parcelize
data class MeaningsResponse(
    @SerializedName("partOfSpeech")
    val partOfSpeech: String?,
    @SerializedName("definitions")
    val definitions: List<DefinitionsResponse>?,
    @SerializedName("synonyms")
    val synonyms: List<String>?,
    @SerializedName("antonyms")
    val antonyms: List<String>?
) : Parcelable

@Parcelize
data class DefinitionsResponse(
    @SerializedName("definition")
    val definition: String?,
    @SerializedName("synonyms")
    val synonyms: List<String>?,
    @SerializedName("antonyms")
    val antonyms: List<String>?,
    @SerializedName("example")
    val example: String?
) : Parcelable
