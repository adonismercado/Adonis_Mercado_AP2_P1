package edu.ucne.adonis_mercado_ap2_p1.presentation.list

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.adonis_mercado_ap2_p1.domain.model.Borrame
import edu.ucne.adonis_mercado_ap2_p1.presentation.navigation.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ListBorrameViewModel @Inject constructor(

): ViewModel() {
    private val _state = MutableStateFlow(ListBorrameUiState(isLoading = true))
    val state: StateFlow<ListBorrameUiState> = _state.asStateFlow()
}

data class ListBorrameUiState(
    val isLoading: Boolean = false,
    val borrames: List<Borrame> = emptyList(),
    val navigateToCrear: Boolean = false
)

