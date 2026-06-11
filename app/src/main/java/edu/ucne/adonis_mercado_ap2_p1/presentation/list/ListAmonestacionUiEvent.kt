package edu.ucne.adonis_mercado_ap2_p1.presentation.list

interface ListAmonestacionUiEvent {
    data class Load(val id: Int?): ListAmonestacionUiEvent
    data class Edit(val id: Int?): ListAmonestacionUiEvent
    data object CreateNew: ListAmonestacionUiEvent
    data class NombreFilterChanged(val value: String): ListAmonestacionUiEvent
    data class RazonFilterChanged(val value: String): ListAmonestacionUiEvent
}