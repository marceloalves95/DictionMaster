package br.com.dictionmaster.core.di

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

    }

    private fun domainModule(): Module = module {

    }

    private fun presentationModule(): Module = module {

    }

}