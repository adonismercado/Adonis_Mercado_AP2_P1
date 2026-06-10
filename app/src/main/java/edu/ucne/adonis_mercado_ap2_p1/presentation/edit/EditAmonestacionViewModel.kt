package edu.ucne.adonis_mercado_ap2_p1.presentation.edit

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.adonis_mercado_ap2_p1.domain.model.Amonestacion
import edu.ucne.adonis_mercado_ap2_p1.domain.usecase.DeleteAmonestacionUseCase
import edu.ucne.adonis_mercado_ap2_p1.domain.usecase.GetAmonestacionUseCase
import edu.ucne.adonis_mercado_ap2_p1.domain.usecase.UpsertAmonestacionUseCase
import edu.ucne.adonis_mercado_ap2_p1.domain.usecase.validateMonto
import edu.ucne.adonis_mercado_ap2_p1.domain.usecase.validateNombres
import edu.ucne.adonis_mercado_ap2_p1.domain.usecase.validateRazon
import edu.ucne.adonis_mercado_ap2_p1.presentation.navigation.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditAmonestacionViewModel @Inject constructor(
    private val getAmonestacionUseCase: GetAmonestacionUseCase,
    private val upsertAmonestacionUseCase: UpsertAmonestacionUseCase,
    private val deleteAmonestacionUseCase: DeleteAmonestacionUseCase,
    private val savedStateHandle: SavedStateHandle,
): ViewModel() {
    private val routeArgs = savedStateHandle.toRoute<Screen.AmonestacionForm>()
    private val amonestacionId = routeArgs.id
    private val _state = MutableStateFlow(EditAmonestacionUiState())
    val state: StateFlow<EditAmonestacionUiState> = _state.asStateFlow()

    init {
        onLoad(amonestacionId)
    }

    fun onEvent(event: EditAmonestacionUiEvent) {
        when (event) {
            is EditAmonestacionUiEvent.Load -> onLoad(event.id)
            is EditAmonestacionUiEvent.Save -> onSave()
            is EditAmonestacionUiEvent.Delete -> onDelete()
            is EditAmonestacionUiEvent.NombreChanged -> _state.update {
                it.copy(nombres = event.value, nombresError = null)
            }
            is EditAmonestacionUiEvent.RazonChanged -> _state.update {
                it.copy(razon = event.value, razonError = null)
            }
            is EditAmonestacionUiEvent.MontoChanged -> _state.update {
                it.copy(monto = event.value, montoError = null)
            }
        }
    }

    fun onLoad(id: Int?) {
        if (id == null || id == 0) {
            _state.update {
                it.copy(
                    isNew = true,
                    amonestacionId = null
                )
            }
            return
        }

        viewModelScope.launch {
            val amonestacion = getAmonestacionUseCase(amonestacionId)
            if (amonestacion != null) {
                _state.update {
                    it.copy(
                        isNew = false,
                        amonestacionId = amonestacionId,
                        nombres = amonestacion.nombres,
                        razon = amonestacion.razon,
                        monto = amonestacion.monto
                    )
                }
            } else {
                _state.update {
                    it.copy(
                        isNew = true,
                        amonestacionId = null
                    )
                }
            }
        }
    }

    fun onSave() {
        val nombreValidation = validateNombres(state.value.nombres)
        val razonValidation = validateRazon(state.value.razon)
        val montoValidation = validateMonto(state.value.monto)

        if (!nombreValidation.isValid || !razonValidation.isValid || !montoValidation.isValid) {
            _state.update {
                it.copy(
                    nombresError = nombreValidation.error,
                    razonError = razonValidation.error,
                    montoError = montoValidation.error
                )
            }
            return
        }

        viewModelScope.launch {
            val amonestacion = Amonestacion(
                amonestacionId = state.value.amonestacionId ?: 0,
                nombres = state.value.nombres,
                razon = state.value.razon,
                monto = state.value.monto
            )

            val result = upsertAmonestacionUseCase(amonestacion)
            result.onSuccess { newId ->
                _state.update {
                    it.copy(
                        isNew = false,
                        isSaving = false,
                        saved = true,
                        amonestacionId = newId
                    )
                }
            }.onFailure { error ->
                _state.update {
                    it.copy(
                        isSaving = false,
                        montoError = error.message ?: "Error desconocido."
                    )
                }
            }
        }
    }

    fun onDelete() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isDeleting = true,
                    deleted = false
                )
            }
            deleteAmonestacionUseCase(amonestacionId)
            _state.update {
                it.copy(
                    isDeleting = false,
                    deleted = true
                )
            }
        }
    }
}