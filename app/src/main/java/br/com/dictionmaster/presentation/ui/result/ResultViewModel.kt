package br.com.dictionmaster.presentation.ui.result

import androidx.lifecycle.ViewModel
import br.com.dictionmaster.core.extensions.launch
import br.com.dictionmaster.domain.usecase.GetSearchWordUseCase
import br.com.dictionmaster.domain.usecase.VerifyNumberOfWordsSavedUseCase
import br.com.dictionmaster.network.event.Event
import br.com.dictionmaster.presentation.ui.model.DictionMasterState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ResultViewModel(
    private val getSearchWordUseCase: GetSearchWordUseCase,
    private val verifyNumberOfWordsSavedUseCase: VerifyNumberOfWordsSavedUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<DictionMasterState>(DictionMasterState.Loading)
    val state: StateFlow<DictionMasterState> get() = _state

    fun searchWord(word: String?) = launch {
        getSearchWordUseCase.invoke(word).collect { event ->
            when (event) {
                is Event.Data -> {
                    _state.value = DictionMasterState.ScreenData(event.data)
                }

                is Event.Error -> {
                    _state.value = DictionMasterState.Error(event.error)
                }

                else -> Unit
            }
        }
    }

    fun getNumberOfFoundWords(): Int = verifyNumberOfWordsSavedUseCase.invoke()
}