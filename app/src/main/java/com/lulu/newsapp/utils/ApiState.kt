package com.lulu.newsapp.utils

import com.lulu.newsapp.model.Article

sealed class ApiState{
    class Success(val newsList: List<Article>): ApiState()
    class Error(val s: String): ApiState()
    object Loading: ApiState()
}
