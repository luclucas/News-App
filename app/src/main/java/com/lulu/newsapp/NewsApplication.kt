package com.lulu.newsapp

import android.app.Application
import com.lulu.newsapp.di.appModule
import com.lulu.newsapp.di.networkModule
import com.lulu.newsapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class NewsApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@NewsApplication)
            modules(appModule, networkModule, viewModelModule)
        }
    }
}