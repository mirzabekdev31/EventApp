package uz.gita.mirzabek.example.eventapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.mirzabek.example.eventapp.navigation.NavigationDispatcher
import uz.gita.mirzabek.example.eventapp.navigation.NavigationHandler
import uz.gita.mirzabek.example.eventapp.navigation.Navigator
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {

    @[Binds Singleton]
    fun bindNavigator(navigationDispatcher: NavigationDispatcher): Navigator

    @[Binds Singleton]
    fun bindNavigationHandler(navigationDispatcher: NavigationDispatcher): NavigationHandler
}