package edu.ucne.adonis_mercado_ap2_p1.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.ucne.adonis_mercado_ap2_p1.presentation.edit.EditBorrameScreen
import edu.ucne.adonis_mercado_ap2_p1.presentation.list.ListBorrameScreen

@Composable
fun AppNavHost (
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.BorrameList
    ) {
        composable<Screen.BorrameList> {
            ListBorrameScreen(
                onNew = {
                    navController.navigate(Screen.BorrameForm(0))
                },
                onNavigateToEdit = { id ->
                    navController.navigate(Screen.BorrameForm(id))
                },
                onAddBorrame = {
                    navController.navigate(Screen.BorrameForm(0))
                }
            )
        }

        composable<Screen.BorrameForm> {
            EditBorrameScreen(
                onBack = {
                    navController.navigate(Screen.BorrameList) {
                        popUpTo(Screen.BorrameList) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}