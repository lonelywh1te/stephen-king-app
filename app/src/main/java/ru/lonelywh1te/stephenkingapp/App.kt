package ru.lonelywh1te.stephenkingapp

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import ru.lonelywh1te.stephenkingapp.di.appModule
import ru.lonelywh1te.stephenkingapp.di.dataModule
import ru.lonelywh1te.stephenkingapp.di.domainModule

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(listOf(appModule, domainModule, dataModule))
        }
    }
}