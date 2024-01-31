package br.com.dictionmaster.data.remote

import br.com.dictionmaster.data.remote.api.DictionMasterApi
import br.com.dictionmaster.data.remote.mapper.toSearch
import br.com.dictionmaster.data.source.DataSource
import br.com.dictionmaster.domain.models.Search
import br.com.dictionmaster.network.extensions.parseResponse
import br.com.dictionmaster.network.extensions.toResponse

class DataSourceRemoteImpl(
    private val api: DictionMasterApi
):DataSource.Remote {
    override suspend fun getSearchWord(word: String?): List<Search> {
        return api.getSearchWord(word).parseResponse().toResponse().map { it.toSearch() }
    }
}