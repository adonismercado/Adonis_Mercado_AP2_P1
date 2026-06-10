package edu.ucne.adonis_mercado_ap2_p1.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import edu.ucne.adonis_mercado_ap2_p1.data.local.entity.AmonestacionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AmonestacionDao {
    @Query("SELECT * FROM amonestaciones ORDER BY amonestacionId DESC")
    fun observeAll(): Flow<List<AmonestacionEntity>>

    @Query("SELECT * FROM amonestaciones WHERE amonestacionId = :id")
    suspend fun getById(id: Int): AmonestacionEntity?

    @Upsert
    suspend fun upsert(entity: AmonestacionEntity)

    @Delete
    suspend fun delete(entity: AmonestacionEntity)

    @Query("DELETE FROM amonestaciones WHERE amonestacionId = :id")
    suspend fun deleteById(id: Int)

    @Query("""
        SELECT * FROM amonestaciones
        WHERE(:nombres IS NULL OR nombres LIKE '%' || :nombres || '%')
        AND(:razon IS NULL OR razon LIKE '%' || :razon || '%')
    """)
    fun filterAmonestacion(nombres: String?, razon: String?): Flow<List<AmonestacionEntity>>
}