package edu.ucne.adonis_mercado_ap2_p1.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    data object AmonestacionList : Screen()
    @Serializable
    data class AmonestacionForm(val id: Int) : Screen()
}