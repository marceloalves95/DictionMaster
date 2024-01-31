package br.com.dictionmaster.core.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import br.com.dictionmaster.data.remote.BASE_URL
import br.com.dictionmaster.data.DictionMasterRepositoryImpl
import br.com.dictionmaster.data.local.DataSourceLocalImpl
import br.com.dictionmaster.data.remote.api.DictionMasterApi
import br.com.dictionmaster.data.local.LocalDataStore
import br.com.dictionmaster.data.remote.DataSourceRemoteImpl
import br.com.dictionmaster.data.source.DataSource
import br.com.dictionmaster.domain.repository.DictionMasterRepository
import br.com.dictionmaster.domain.usecase.GetSearchWordUseCase
import br.com.dictionmaster.domain.usecase.SaveWordUseCase
import br.com.dictionmaster.domain.usecase.VerifyNumberOfWordsUseCase
import br.com.dictionmaster.network.others.Interceptor
import br.com.dictionmaster.network.others.Service
import br.com.dictionmaster.presentation.ui.result.ResultViewModel
import br.com.dictionmaster.presentation.ui.search.SearchViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object DictionMasterModule {

    private const val PREFERENCES_FILE_KEY = "br.com.dictionmaster.app"

    fun load() {
        loadKoinModules(
            listOf(
                dataModule(),
                domainModule(),
                presentationModule()
            )
        )
    }

    private fun provideSharedPreferences(app: Application): SharedPreferences =
        app.getSharedPreferences(PREFERENCES_FILE_KEY, Context.MODE_PRIVATE)


    private fun dataModule(): Module = module {
        factory<DictionMasterApi> {
            Service(context = get()).createService(baseUrl = BASE_URL)
        }
        factory {
            Interceptor(context = get())
        }
        single { provideSharedPreferences(androidApplication()) }
        single { LocalDataStore(get()) }
        single <DataSource.Local>{
            DataSourceLocalImpl(localDataStore = get())
        }
        single <DataSource.Remote>{
            DataSourceRemoteImpl(api = get())
        }
        single<DictionMasterRepository> {
            DictionMasterRepositoryImpl(
                dataSourceLocal = get(),
                dataSourceRemote = get()
            )
        }
    }

    private fun domainModule(): Module = module {
        factory {
            GetSearchWordUseCase(repository = get())
        }
        factory {
            SaveWordUseCase(repository = get())
        }
        factory {
            VerifyNumberOfWordsUseCase(repository = get())
        }
    }

    private fun presentationModule(): Module = module {
        viewModel {
            ResultViewModel(
                getSearchWordUseCase = get()
            )
        }
        viewModel {
            SearchViewModel(
                saveWordUseCase = get(),
                verifyNumberOfWordsUseCase = get()
            )
        }
    }

}