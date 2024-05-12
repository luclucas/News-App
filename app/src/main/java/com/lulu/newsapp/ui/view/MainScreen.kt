package com.lulu.newsapp.ui.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lulu.newsapp.ui.view.components.NewsList
import com.lulu.newsapp.ui.viewmodel.NewsViewModel
import com.lulu.newsapp.utils.ApiState

@Composable
fun MainScreen(viewModel: NewsViewModel, navController: NavController) {

    LaunchedEffect(key1 = Unit, block ={
        viewModel.getTopNews("br")
    } )

    val uiState = viewModel.uiState.collectAsState()


    Column(
        Modifier.fillMaxSize(),
    ) {

        SearchBar(navController)

        Text(
            modifier = Modifier
                .padding(top = 20.dp, start = 20.dp, end = 20.dp, bottom = 20.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = "Últimas Notícias",
            style = MaterialTheme.typography.headlineLarge
        )

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
                NewsList(state.newsList)
            }

            is ApiState.Error -> {

            }
        }

    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(navController: NavController) {
    var searchText by remember {
        mutableStateOf("")
    }
    Row(
        Modifier
            .padding(20.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        OutlinedTextField(
            modifier = Modifier.padding(end = 10.dp),
            value = searchText,
            label = { Text(text = "Buscar")},
            onValueChange = { searchText = it },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black,
                disabledBorderColor = Color.Black
            )
        )

        Icon(
            imageVector = Icons.Outlined.Search,
            contentDescription = "search icon",
            Modifier.clickable {
                navController.navigate("article/$searchText")
            })
    }

}

