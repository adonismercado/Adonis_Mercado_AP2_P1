package edu.ucne.adonis_mercado_ap2_p1.presentation.edit

data class EditAmonestacionUiState(
    val amonestacionId: Int? = 0,
    val nombres: String = "",
    val razon: String = "",
    val monto: String = "",
    val nombresError: String? = null,
    val razonError: String? = null,
    val montoError: String? = null,
    val isNew: Boolean = true,
    val isSaving: Boolean = false,
    val isDeleting: Boolean = false,
    val saved: Boolean = false,
    val deleted: Boolean = false,
)