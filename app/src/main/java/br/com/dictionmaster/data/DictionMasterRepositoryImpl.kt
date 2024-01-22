package br.com.dictionmaster.data

import br.com.dictionmaster.data.api.DictionMasterApi
import br.com.dictionmaster.data.mapper.toSearch
import br.com.dictionmaster.domain.models.Search
import br.com.dictionmaster.domain.repository.DictionMasterRepository
import br.com.dictionmaster.network.extensions.parseResponse
import br.com.dictionmaster.network.extensions.toResponse

class DictionMasterRepositoryImpl(
    private val api: DictionMasterApi
) : DictionMasterRepository {
    override suspend fun getSearchWord(word: String?): List<Search> {
        return api.getSearchWord(word).parseResponse().toResponse().map { it.toSearch() }
    }

}