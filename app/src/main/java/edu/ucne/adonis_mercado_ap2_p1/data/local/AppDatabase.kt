package edu.ucne.adonis_mercado_ap2_p1.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.ucne.adonis_mercado_ap2_p1.data.local.dao.AmonestacionDao
import edu.ucne.adonis_mercado_ap2_p1.data.local.entity.AmonestacionEntity

@Database(
    entities = [
        AmonestacionEntity::class
    ],
    version = 1,
    exportSchema = false
)


abstract class AppDatabase : RoomDatabase() {
    abstract fun amonestacionDao(): AmonestacionDao
}