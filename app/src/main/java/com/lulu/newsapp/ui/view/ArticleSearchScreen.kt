package com.lulu.newsapp.ui.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lulu.newsapp.ui.view.components.NewsList
import com.lulu.newsapp.ui.viewmodel.NewsViewModel
import com.lulu.newsapp.utils.ApiState

@Composable
fun ArticleScreen(viewModel: NewsViewModel, query: String, navController: NavController){
    LaunchedEffect(key1 = Unit, block = {
        viewModel.getBySearch(query)

    })
    val uiState = viewModel.uiState.collectAsState()


    Column(
        Modifier.fillMaxSize(),
    ) {

        when (val state = uiState.value) {
            is ApiState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,

                    ) {
                    CircularProgressIndicator()
                }
            }

            is ApiState.Success -> {
                Text(query.replaceFirstChar{it.uppercase()}, modifier = Modifier.padding(20.dp).fillMaxWidth(), textAlign = TextAlign.Center, style = MaterialTheme.typography.headlineLarge)
                NewsList(state.newsList)
            }

            is ApiState.Error -> {

            }
        }

    }
}