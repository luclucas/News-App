package com.lulu.newsapp.data.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

val BASE_URL = "https://newsapi.org/v2/"


fun getRetrofitInstance():Retrofit{
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun getNewsService(retrofit: Retrofit) = retrofit.create(NewsService::class.java)