package com.example.soop2025.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.soop2025.presentation.ui.screen.ReposDetailScreen
import com.example.soop2025.presentation.ui.screen.ReposSearchScreen

@Composable
fun MainNavHost(
    navController: NavHostController,
    reposSearchViewModel: ReposSearchViewModel
) {
    NavHost(
        navController = navController,
        startDestination = "깃허브_레포_검색",
        modifier = Modifier.fillMaxSize()
    ) {
        composable(
            route = "깃허브_레포_검색"
        ) {
            ReposSearchScreen(
                reposSearchViewModel
            ) { userName, repoName -> // 얘네를 넘겨서
                navController.navigate("깃허브_레포_상세/$userName/$repoName")
            }
        }
        composable(
            route = "깃허브_레포_상세/{userName}/{repoName}"
        ) {
            ReposDetailScreen()
        }
    }
}
