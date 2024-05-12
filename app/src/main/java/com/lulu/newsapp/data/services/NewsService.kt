package com.lulu.newsapp.data.services

import com.lulu.newsapp.model.NewsModel
import com.lulu.newsapp.utils.API_KEY
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsService {

    @GET("top-headlines")
    fun getTopNews(
        @Query("country") country: String?,
        @Query("apiKey") key: String = API_KEY
    ): Call<NewsModel>

    @GET("everything")
    fun getBySearch(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("apiKey") key: String = API_KEY
    ): Call<NewsModel>
}