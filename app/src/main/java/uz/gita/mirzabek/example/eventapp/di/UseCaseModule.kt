package uz.gita.mirzabek.example.eventapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.mirzabek.example.eventapp.domain.usecase.AppUseCase
import uz.gita.mirzabek.example.eventapp.domain.usecase.impl.AppUseCaseImpl


@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {

    @Binds
    fun bindAppUseCase(appUseCaseImpl: AppUseCaseImpl): AppUseCase
}