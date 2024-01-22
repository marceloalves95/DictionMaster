package br.com.dictionmaster.presentation.ui.model

import br.com.dictionmaster.domain.models.Search

sealed class DictionMasterState {
    object Loading: DictionMasterState()
    data class ScreenData(val search:List<Search>) : DictionMasterState()
    data class Error(val exception: Throwable? = null) : DictionMasterState()
}