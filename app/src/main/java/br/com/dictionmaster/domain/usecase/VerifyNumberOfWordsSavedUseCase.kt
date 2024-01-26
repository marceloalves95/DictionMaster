package br.com.dictionmaster.domain.usecase

import br.com.dictionmaster.domain.repository.DictionMasterRepository

class VerifyNumberOfWordsSavedUseCase(
    private val repository: DictionMasterRepository
) {
    operator fun invoke(): Int {
        return repository.getNumberOfSavedWordsFromLocalData()
    }

}