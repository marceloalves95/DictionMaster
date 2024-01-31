package br.com.dictionmaster.data.local

import br.com.dictionmaster.data.source.DataSource

class DataSourceLocalImpl(
    private val localDataStore: LocalDataStore
) : DataSource.Local {
    override fun getList(): ArrayList<String> {
        return localDataStore.getList()
    }

    override fun saveList(list: List<String>) {
        localDataStore.saveList(list)
    }
}