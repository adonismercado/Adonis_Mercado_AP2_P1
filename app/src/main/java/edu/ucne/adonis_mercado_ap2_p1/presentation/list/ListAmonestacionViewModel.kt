package edu.ucne.adonis_mercado_ap2_p1.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.adonis_mercado_ap2_p1.domain.usecase.FilterAmonestacionUseCase
import edu.ucne.adonis_mercado_ap2_p1.domain.usecase.ObserveAmonestacionUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListAmonestacionViewModel @Inject constructor(
    private val observeAmonestacionUseCase: ObserveAmonestacionUseCase,
    private val filterAmonestacionUseCase: FilterAmonestacionUseCase,
): ViewModel() {
    private val _state = MutableStateFlow(ListAmonestacionUiState(isLoading = true))
    val state: StateFlow<ListAmonestacionUiState> = _state.asStateFlow()

    init {
        onLoad()
    }

    fun onEvent(event: ListAmonestacionUiEvent) {
        when (event) {
            is ListAmonestacionUiEvent.Load -> onLoad()
            is ListAmonestacionUiEvent.CreateNew -> _state.update {
                it.copy(navigateToCreate = true)
            }
            is ListAmonestacionUiEvent.Edit -> _state.update {
                it.copy(navigateToEditId = event.id)
            }
            is ListAmonestacionUiEvent.NombreFilterChanged -> {
                _state.update {
                    it.copy(nombresFilter = event.value)
                }
                onFilter(nombresFil = event.value)
            }
            is ListAmonestacionUiEvent.RazonFilterChanged -> {
                _state.update {
                    it.copy(razonFilter = event.value)
                }
                onFilter(razonFil = event.value)
            }
        }
    }

    fun onLoad() {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            observeAmonestacionUseCase().collectLatest { list ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        amonestaciones = list
                    )
                }
            }
        }
    }

    fun onFilter(
        nombresFil: String = state.value.nombresFilter,
        razonFil: String = state.value.razonFilter
    ) {
        _state.update {
            it.copy(
                nombresFilter = nombresFil,
                razonFilter = razonFil
            )
        }

        viewModelScope.launch {
            if (nombresFil.isBlank() && razonFil.isBlank()) {
                onLoad()
            } else {
                _state.update {
                    it.copy(isLoading = true)
                }
                filterAmonestacionUseCase(nombresFil, razonFil).collectLatest { list ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            amonestaciones = list
                        )
                    }
                }
            }
        }
    }
}
