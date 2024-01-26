package br.com.dictionmaster.domain.repository

import br.com.dictionmaster.domain.models.Search

interface DictionMasterRepository {
    suspend fun getSearchWord(word:String?): List<Search>
    fun getNumberOfSavedWordsFromLocalData(): Int
}