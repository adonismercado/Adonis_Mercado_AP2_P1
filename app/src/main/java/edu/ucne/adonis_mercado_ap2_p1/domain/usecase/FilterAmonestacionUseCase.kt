package edu.ucne.adonis_mercado_ap2_p1.domain.usecase

import edu.ucne.adonis_mercado_ap2_p1.domain.model.Amonestacion
import edu.ucne.adonis_mercado_ap2_p1.domain.repository.AmonestacionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FilterAmonestacionUseCase @Inject constructor(
    private val repository: AmonestacionRepository
) {
    operator fun invoke(nombres: String?, razon: String?): Flow<List<Amonestacion>> = repository.fiterAmonestaciones(nombres, razon)
}