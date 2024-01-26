package br.com.dictionmaster.data

import br.com.dictionmaster.data.api.DictionMasterApi
import br.com.dictionmaster.data.local.LocalDataStore
import br.com.dictionmaster.data.mapper.toSearch
import br.com.dictionmaster.domain.models.Search
import br.com.dictionmaster.domain.repository.DictionMasterRepository
import br.com.dictionmaster.network.extensions.parseResponse
import br.com.dictionmaster.network.extensions.toResponse
import br.com.dictionmaster.network.model.ServiceState

class DictionMasterRepositoryImpl(
    private val api: DictionMasterApi,
    private val localDataStore: LocalDataStore
) : DictionMasterRepository {
    override suspend fun getSearchWord(word: String?): List<Search> {
        val response = api.getSearchWord(word).parseResponse()
        when(response){
            is ServiceState.Success -> {
                val numberOfWords = localDataStore.getNumberOfFoundWords()
                if(numberOfWords < 10){
                    val increment = numberOfWords + 1
                    localDataStore.storeNumberOfFoundWords(increment)
                }
            }
            else -> Unit
        }
        return response.toResponse().map { it.toSearch() }
    }

    override fun getNumberOfSavedWordsFromLocalData(): Int {
        return localDataStore.getNumberOfFoundWords()
    }

}