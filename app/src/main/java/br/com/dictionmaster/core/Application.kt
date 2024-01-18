package br.com.dictionmaster.core

import android.app.Application
import br.com.dictionmaster.core.di.DictionMasterModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Application)
        }
        DictionMasterModule.load()
    }
}