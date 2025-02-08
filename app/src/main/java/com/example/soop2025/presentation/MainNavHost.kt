package com.example.soop2025.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.soop2025.presentation.ui.screen.ReposDetailScreen
import com.example.soop2025.presentation.ui.screen.ReposSearchScreen

@Composable
fun MainNavHost(
    navController: NavHostController,
    reposSearchViewModel: ReposSearchViewModel,
    reposViewModel: ReposViewModel
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
            route = "깃허브_레포_상세/{userName}/{repoName}",
            arguments = listOf(
                navArgument("userName") { type = NavType.StringType },
                navArgument("repoName") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val userName = backStackEntry.arguments?.getString("userName") ?: ""
            val repoName = backStackEntry.arguments?.getString("repoName") ?: ""

            ReposDetailScreen(
                reposViewModel,
                userName,
                repoName
            )
        }
    }
}
