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
        startDestination = "borrame_list"
    ) {
        composable("borrame_list") {
            ListBorrameScreen(
                onAddBorrame = {
                    navController.navigate("borrame_form(0)")
                },
                onNavigateToEdit = { id ->
                    navController.navigate("borrame_form(id)")
                }
            )
        }

        composable("borrame_form") {
            EditBorrameScreen(
                onBack = {
                    navController.navigate("borrame_list") {
                        popUpTo("borrame_list") {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}