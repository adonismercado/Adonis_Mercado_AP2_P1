package edu.ucne.adonis_mercado_ap2_p1.domain.usecase

import edu.ucne.adonis_mercado_ap2_p1.domain.model.Amonestacion
import edu.ucne.adonis_mercado_ap2_p1.domain.repository.AmonestacionRepository
import javax.inject.Inject

class UpsertAmonestacionUseCase @Inject constructor(
    private val repository: AmonestacionRepository
) {
    suspend operator fun invoke(amonestacion: Amonestacion): Result<Int> {
        val nombresResult = validateNombres(amonestacion.nombres)
        if (!nombresResult.isValid) {
            return Result.failure(IllegalArgumentException(nombresResult.error))
        }

        val razonResult = validateRazon(amonestacion.razon)
        if (!razonResult.isValid) {
            return Result.failure(IllegalArgumentException(razonResult.error))
        }

        val montoResult = validateMonto(amonestacion.monto)
        if (!montoResult.isValid) {
            return Result.failure(IllegalArgumentException(montoResult.error))
        }

        return runCatching { repository.upsert(amonestacion) }
    }
}