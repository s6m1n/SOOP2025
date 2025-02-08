package com.example.soop2025.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.soop2025.presentation.ui.screen.ReposScreen
import com.example.soop2025.presentation.ui.screen.ReposSearchScreen

@Composable
fun MainNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = ROUTE_REPOS_SEARCH,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(
            route = ROUTE_REPOS_SEARCH
        ) {
            ReposSearchScreen(
                onMoveReposScreen = { userName, repoName ->
                    navController.navigate(ROUTE_REPOS_FORMAT.format(userName, repoName))
                }
            )
        }
        composable(
            route = ROUTE_REPOS,
            arguments = listOf(
                navArgument(ARG_USER_NAME) { type = NavType.StringType },
                navArgument(ARG_REPOS_NAME) { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val userName = backStackEntry.arguments?.getString(ARG_USER_NAME) ?: ""
            val repoName = backStackEntry.arguments?.getString(ARG_REPOS_NAME) ?: ""

            ReposScreen(
                userName = userName,
                repoName = repoName
            )
        }
    }
}

private const val ARG_USER_NAME = "userName"
private const val ARG_REPOS_NAME = "repoName"

private const val ROUTE_REPOS_SEARCH = "repos_search"
private const val REPOS = "repos"
private const val ROUTE_REPOS = "$REPOS/{$ARG_USER_NAME}/{$ARG_REPOS_NAME}"
private const val ROUTE_REPOS_FORMAT = "$REPOS/%s/%s"
