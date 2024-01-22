package br.com.dictionmaster.core.di

import br.com.dictionmaster.data.BASE_URL
import br.com.dictionmaster.data.DictionMasterRepositoryImpl
import br.com.dictionmaster.data.api.DictionMasterApi
import br.com.dictionmaster.domain.repository.DictionMasterRepository
import br.com.dictionmaster.domain.usecase.GetSearchWordUseCase
import br.com.dictionmaster.network.service.Service
import br.com.dictionmaster.presentation.ui.result.ResultViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object DictionMasterModule {

    fun load() {
        loadKoinModules(
            listOf(
                dataModule(),
                domainModule(),
                presentationModule()
            )
        )
    }

    private fun dataModule(): Module = module {
        factory<DictionMasterApi> {
            Service.createService(
                baseUrl = BASE_URL
            )
        }
        single<DictionMasterRepository> {
            DictionMasterRepositoryImpl(api = get())
        }
    }

    private fun domainModule(): Module = module {
        factory {
            GetSearchWordUseCase(repository = get())
        }
    }

    private fun presentationModule(): Module = module {
        viewModel {
            ResultViewModel(getSearchWordUseCase = get())
        }
    }

}