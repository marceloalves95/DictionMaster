package br.com.dictionmaster.data

import br.com.dictionmaster.data.source.DataSource
import br.com.dictionmaster.domain.models.Search
import br.com.dictionmaster.domain.repository.DictionMasterRepository

class DictionMasterRepositoryImpl(
    private val dataSourceLocal: DataSource.Local,
    private val dataSourceRemote: DataSource.Remote
) : DictionMasterRepository {
    override suspend fun getSearchWord(word: String?): List<Search> {
        return dataSourceRemote.getSearchWord(word)
    }

    override fun getList(): ArrayList<String> {
        return dataSourceLocal.getList()
    }

    override fun saveList(list: List<String>) {
        dataSourceLocal.saveList(list)
    }

}