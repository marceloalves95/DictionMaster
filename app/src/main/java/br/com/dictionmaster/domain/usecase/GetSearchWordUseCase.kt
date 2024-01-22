package br.com.dictionmaster.domain.usecase

import br.com.dictionmaster.core.extensions.executeFlow
import br.com.dictionmaster.domain.repository.DictionMasterRepository

class GetSearchWordUseCase(
    private val repository: DictionMasterRepository
) {
    suspend operator fun invoke(word: String?) = executeFlow(
        getRepository = {
            repository.getSearchWord(word)
        }
    )
}