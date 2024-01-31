package br.com.dictionmaster.data.source

import br.com.dictionmaster.domain.models.Search

sealed interface DataSource {
    interface Local : DataSource {
        fun getList(): ArrayList<String>
        fun saveList(list: List<String>)

    }

    interface Remote : DataSource {
        suspend fun getSearchWord(word: String?): List<Search>
    }
}