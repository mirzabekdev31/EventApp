package uz.gita.mirzabek.example.eventapp.navigation

import androidx.navigation.NavDirections

interface Navigator {

    suspend fun navigateTo(direction: NavDirections)

    suspend fun navigationUp()
}