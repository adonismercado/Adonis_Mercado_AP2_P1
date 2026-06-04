package edu.ucne.adonis_mercado_ap2_p1.presentation.edit

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class EditBorrameViewModel @Inject constructor(

): ViewModel() {
    private val _state = MutableStateFlow(EditBorrameUiState())
    val state: StateFlow<EditBorrameUiState> = _state.asStateFlow()
}

data class EditBorrameUiState(
    val isNew: Boolean = false,
    val isSaving: Boolean  = false,
    val dato: String = ""
)