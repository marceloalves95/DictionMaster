package br.com.dictionmaster.domain.repository

import br.com.dictionmaster.domain.models.Search
import retrofit2.http.Path

interface DictionMasterRepository {
    suspend fun getSearchWord(word:String?): List<Search>
}