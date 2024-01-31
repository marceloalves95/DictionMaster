package br.com.dictionmaster.domain.usecase

import br.com.dictionmaster.domain.repository.DictionMasterRepository

class VerifyNumberOfWordsUseCase(
    private val repository: DictionMasterRepository
) {
    operator fun invoke(): ArrayList<String> {
        return repository.getList()
    }
}