package edu.ucne.adonis_mercado_ap2_p1.domain.repository

import edu.ucne.adonis_mercado_ap2_p1.domain.model.Amonestacion
import kotlinx.coroutines.flow.Flow

interface AmonestacionRepository {
    fun observeAmonestaciones(): Flow<List<Amonestacion>>
    suspend fun getAmonestacion(): Amonestacion?
    suspend fun upsert(amonestacion: Amonestacion): Int
    suspend fun delete(id: Int)
    fun fiterAmonestaciones(nombres: String?, razon: String?): Flow<List<Amonestacion>>
}