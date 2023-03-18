package uz.gita.mirzabek.example.eventapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.mirzabek.example.eventapp.domain.repository.AppRepository
import uz.gita.mirzabek.example.eventapp.domain.repository.impl.AppRepositoryImpl


@Module
@InstallIn(SingletonComponent::class)
interface AppRepositoryModule {

    @Binds
    fun bindAppRepository(appRepositoryImpl: AppRepositoryImpl): AppRepository
}