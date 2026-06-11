package edu.ucne.adonis_mercado_ap2_p1.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.ucne.adonis_mercado_ap2_p1.presentation.edit.EditAmonestacionScreen
import edu.ucne.adonis_mercado_ap2_p1.presentation.list.ListAmonestacionScreen

@Composable
fun AppNavHost (
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.AmonestacionList
    ) {
        composable<Screen.AmonestacionList> {
            ListAmonestacionScreen(
                onNew = {
                    navController.navigate(Screen.AmonestacionForm(0))
                },
                onNavigateToEdit = { id ->
                    navController.navigate(Screen.AmonestacionForm(id))
                },
            )
        }

        composable<Screen.AmonestacionForm> {
            EditAmonestacionScreen(
                onBack = {
                    navController.navigate(Screen.AmonestacionList) {
                        popUpTo(Screen.AmonestacionList) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}