package com.rami.moviesapp.ui.base

import android.app.Application
import com.rami.moviesapp.di.networkModule
import com.rami.moviesapp.di.repositoryModule
import com.rami.moviesapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Created by Rami El-bouhi on 24,October,2021
 */
class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BaseApplication)
            modules(listOf(networkModule, repositoryModule, viewModelModule))
        }
    }
}