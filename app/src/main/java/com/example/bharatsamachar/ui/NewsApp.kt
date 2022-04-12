package com.example.bharatsamachar.ui

import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bharatsamachar.BottomMenuScreen
import com.example.bharatsamachar.components.BottomMenu
import com.example.bharatsamachar.models.TopNewsArticles
import com.example.bharatsamachar.network.NewsManager
import com.example.bharatsamachar.ui.screen.Categories
import com.example.bharatsamachar.ui.screen.DetailScreen
import com.example.bharatsamachar.ui.screen.Sources
import com.example.bharatsamachar.ui.screen.TopNews

@Composable
fun NewsApp() {
    val scrollState = rememberScrollState()
    val navController = rememberNavController()
    MainScreen(navController = navController, scrollState = scrollState)
}

@Composable
fun MainScreen(navController: NavHostController, scrollState: ScrollState) {
    Scaffold(bottomBar = {
        BottomMenu(navController = navController)
    }) {
        Navigation(navController = navController, scrollState = scrollState, paddingValues = it)
    }
}

@Composable
fun Navigation(
    navController: NavHostController,
    scrollState: ScrollState,
    newsManage: NewsManager = NewsManager(),
    paddingValues: PaddingValues
) {

    val articles = newsManage.newsResponse.value.articles
    Log.d("news", "$articles")
    articles?.let {
        NavHost(
            navController = navController,
            startDestination =
            BottomMenuScreen.TopNews.route,
            modifier = Modifier.padding(paddingValues = paddingValues)
        ) {
            bottomNavigation(navController = navController, articles = articles, newsManage)
            composable(
                "Detail/{index}",
                arguments = listOf(navArgument("index") { type = NavType.IntType })
            ) { navBackStackEntry ->
                val index = navBackStackEntry.arguments?.getInt("index")
                index?.let {
                    val articles = articles[index]
                    DetailScreen(articles, scrollState, navController)
                }

            }
        }
    }
}

fun NavGraphBuilder.bottomNavigation(
    navController: NavController,
    articles: List<TopNewsArticles>, newsManage: NewsManager
) {
    composable(BottomMenuScreen.TopNews.route) {
        TopNews(navController = navController, articles = articles)
    }

    composable(BottomMenuScreen.Categories.route) {
        newsManage.getArticlesByCategory("business")
        newsManage.onSelectedCategoryChanged("business")

        Categories(newsManager = newsManage, onFetchCategory = {
            newsManage.onSelectedCategoryChanged(it)
            newsManage.getArticlesByCategory(it)
        })
    }

    composable(BottomMenuScreen.Sources.route) {
        Sources(newsManager = newsManage)
    }
}