package edu.ucne.adonis_mercado_ap2_p1.domain.usecase

import edu.ucne.adonis_mercado_ap2_p1.domain.model.Amonestacion
import edu.ucne.adonis_mercado_ap2_p1.domain.repository.AmonestacionRepository
import javax.inject.Inject

class GetAmonestacionUseCase @Inject constructor(
    private val repository: AmonestacionRepository
){
    suspend operator fun invoke(id: Int): Amonestacion? = repository.getAmonestacion(id)
}