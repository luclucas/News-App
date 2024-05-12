package com.lulu.newsapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lulu.newsapp.data.repositories.NewsRepository
import com.lulu.newsapp.model.NewsModel
import com.lulu.newsapp.utils.ApiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel(private val repository: NewsRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<ApiState>(ApiState.Loading)
    val uiState: StateFlow<ApiState> = _uiState
    val errorMessage = MutableLiveData<String>()



    fun getTopNews(country:String?) {
        viewModelScope.launch {

            val request = repository.getTopNews(country)
            request.enqueue(object : Callback<NewsModel> {
                override fun onResponse(callback: Call<NewsModel>, response: Response<NewsModel>) {
                    Log.d("teste", "$response")
                    if (response.isSuccessful) {
                        _uiState.value = ApiState.Success(response.body()!!.articles)
                    } else {
                       _uiState.value = ApiState.Error("Error")
                    }
                }

                override fun onFailure(p0: Call<NewsModel>, p1: Throwable) {
                    _uiState.value = ApiState.Error("Error")

                }
            })
        }
    }

    fun getBySearch(query: String){
        _uiState.value = ApiState.Loading
        viewModelScope.launch {

            val request = repository.getBySearch(query)
            request.enqueue(object : Callback<NewsModel> {
                override fun onResponse(callback: Call<NewsModel>, response: Response<NewsModel>) {
                    Log.d("teste", "$response")
                    if (response.isSuccessful) {
                        _uiState.value = ApiState.Success(response.body()!!.articles)
                    } else {
                        _uiState.value = ApiState.Error("Error")
                    }
                }

                override fun onFailure(p0: Call<NewsModel>, p1: Throwable) {
                    _uiState.value = ApiState.Error("Error")

                }
            })
        }
    }

}