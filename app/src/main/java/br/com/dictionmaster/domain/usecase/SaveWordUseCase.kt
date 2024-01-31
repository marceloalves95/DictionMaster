package br.com.dictionmaster.domain.usecase

import br.com.dictionmaster.domain.repository.DictionMasterRepository

class SaveWordUseCase(
    private val repository: DictionMasterRepository
) {
    operator fun invoke(list: List<String>) {
        repository.saveList(list)
    }

}