package edu.ucne.adonis_mercado_ap2_p1.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import edu.ucne.adonis_mercado_ap2_p1.data.repository.AmonestacionRepositoryImpl
import edu.ucne.adonis_mercado_ap2_p1.domain.repository.AmonestacionRepository
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindAmonestacionRepository(
        impl: AmonestacionRepositoryImpl
    ): AmonestacionRepository
}