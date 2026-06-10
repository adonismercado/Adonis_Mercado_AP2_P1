package edu.ucne.adonis_mercado_ap2_p1.presentation.edit

interface EditAmonestacionUiEvent {
    data class Load(val id: Int?): EditAmonestacionUiEvent
    data class NombreChanged(val value: String): EditAmonestacionUiEvent
    data class RazonChanged(val value: String): EditAmonestacionUiEvent
    data class MontoChanged(val value: String): EditAmonestacionUiEvent
    data object Save: EditAmonestacionUiEvent
    data object Delete: EditAmonestacionUiEvent
}