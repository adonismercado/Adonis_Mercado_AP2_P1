package edu.ucne.adonis_mercado_ap2_p1.presentation.list

import edu.ucne.adonis_mercado_ap2_p1.domain.model.Amonestacion

data class ListAmonestacionUiState (
    val isLoading: Boolean = false,
    val amonestaciones: List<Amonestacion> = emptyList(),
    val navigateToCreate: Boolean = false,
    val navigateToEditId: Int? = null,
    val nombresFilter: String = "",
    val razonFilter: String = "",
)