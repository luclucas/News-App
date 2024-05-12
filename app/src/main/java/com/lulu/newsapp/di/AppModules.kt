package com.lulu.newsapp.di

import com.lulu.newsapp.data.repositories.NewsRepository
import com.lulu.newsapp.data.services.NewsService
import com.lulu.newsapp.data.services.getNewsService
import com.lulu.newsapp.data.services.getRetrofitInstance
import com.lulu.newsapp.ui.viewmodel.NewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        NewsRepository(get())
    }
    single {

    }
}

val networkModule = module {
    single {
        getRetrofitInstance()
    }
    single { getNewsService(get()) }
}

val viewModelModule = module {
    viewModel {
        NewsViewModel(get())
    }
}