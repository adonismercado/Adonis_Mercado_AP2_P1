package edu.ucne.adonis_mercado_ap2_p1.data.repository

import edu.ucne.adonis_mercado_ap2_p1.data.local.dao.AmonestacionDao
import edu.ucne.adonis_mercado_ap2_p1.data.mapper.toDomain
import edu.ucne.adonis_mercado_ap2_p1.data.mapper.toEntity
import edu.ucne.adonis_mercado_ap2_p1.domain.model.Amonestacion
import edu.ucne.adonis_mercado_ap2_p1.domain.repository.AmonestacionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AmonestacionRepositoryImpl @Inject constructor(
    private val dao: AmonestacionDao
) : AmonestacionRepository {
    override fun observeAmonestaciones(): Flow<List<Amonestacion>> = dao.observeAll().map { list ->
        list.map { it.toDomain() }
    }

    override suspend fun getAmonestacion(id: Int): Amonestacion? = dao.getById(id)?.toDomain()

    override suspend fun upsert(amonestacion: Amonestacion): Int {
        dao.upsert(amonestacion.toEntity())
        return amonestacion.amonestacionId
    }

    override suspend fun delete(id: Int) {
        dao.deleteById(id)
    }

    override fun fiterAmonestaciones(nombres: String?, razon: String?): Flow<List<Amonestacion>> = dao.filterAmonestacion(nombres, razon).map { list ->
        list.map { it.toDomain() }
    }
}