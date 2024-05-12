package com.lulu.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lulu.newsapp.ui.theme.NewsAppTheme
import com.lulu.newsapp.ui.view.ArticleScreen
import com.lulu.newsapp.ui.view.MainScreen
import com.lulu.newsapp.ui.viewmodel.NewsViewModel
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = koinViewModel<NewsViewModel>()

            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "main"){
                composable("main"){
                    MainScreen(viewModel, navController)
                }
                composable("article/{query}"){ backStackEntry ->
                    val query = backStackEntry.arguments?.getString("query") ?: ""
                    ArticleScreen(viewModel = viewModel, query, navController)
                }
            }
        }
    }
}