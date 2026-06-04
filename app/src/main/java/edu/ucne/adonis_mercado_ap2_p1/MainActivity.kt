package edu.ucne.adonis_mercado_ap2_p1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import edu.ucne.adonis_mercado_ap2_p1.presentation.navigation.AppNavHost
import edu.ucne.adonis_mercado_ap2_p1.ui.theme.Adonis_Mercado_AP2_P1Theme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Adonis_Mercado_AP2_P1Theme {
                val navController = rememberNavController()

                AppNavHost(navController = navController)
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Adonis_Mercado_AP2_P1Theme {
        Greeting("Android")
    }
}