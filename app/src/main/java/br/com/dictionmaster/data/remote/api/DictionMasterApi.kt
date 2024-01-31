package br.com.dictionmaster.data.remote.api

import br.com.dictionmaster.data.remote.models.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DictionMasterApi {
    @GET("api/v2/entries/en/{word}")
    suspend fun getSearchWord(@Path("word") word: String?): Response<List<SearchResponse>>
}