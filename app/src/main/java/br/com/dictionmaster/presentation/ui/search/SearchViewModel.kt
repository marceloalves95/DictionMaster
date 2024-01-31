package br.com.dictionmaster.presentation.ui.search

import androidx.lifecycle.ViewModel
import br.com.dictionmaster.domain.usecase.SaveWordUseCase
import br.com.dictionmaster.domain.usecase.VerifyNumberOfWordsUseCase

class SearchViewModel(
    private val saveWordUseCase: SaveWordUseCase,
    private val verifyNumberOfWordsUseCase: VerifyNumberOfWordsUseCase
) : ViewModel() {

    private val listOfWords = mutableListOf<String>()

    fun saveListOfWords(word: String) {
        listOfWords.add(word)
        saveWordUseCase.invoke(listOfWords.distinct())
    }

    private fun getListOfWords(): List<String> = verifyNumberOfWordsUseCase.invoke()

    fun verifyNumberOfWords(
        openPurchase: () -> Unit,
        openResult: () -> Unit
    ) {
        if (getListOfWords().size < FOUR) {
            openResult.invoke()
        } else {
            openPurchase.invoke()
        }
    }

    companion object {
        const val FOUR = 4
    }


}