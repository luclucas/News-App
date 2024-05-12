package com.lulu.newsapp.data.repositories

import com.lulu.newsapp.data.services.NewsService

class NewsRepository(private val service: NewsService) {

    private val DEFAULT_PAGE_SIZE = 15

    fun getTopNews(country: String?) = service.getTopNews(country)

    fun getBySearch(query: String, page: Int = 1) =
        service.getBySearch(query, page, DEFAULT_PAGE_SIZE)

}