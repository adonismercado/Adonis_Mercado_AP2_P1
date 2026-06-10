package edu.ucne.adonis_mercado_ap2_p1.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import edu.ucne.adonis_mercado_ap2_p1.data.local.AppDatabase
import edu.ucne.adonis_mercado_ap2_p1.data.local.dao.AmonestacionDao
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    @Singleton
    fun provideAmonestacionDb(@ApplicationContext appContext: Context) =
        Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "Amonestaciones.db"
        ).fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideAmonestacionDao(database: AppDatabase): AmonestacionDao {
        return database.amonestacionDao()
    }
}