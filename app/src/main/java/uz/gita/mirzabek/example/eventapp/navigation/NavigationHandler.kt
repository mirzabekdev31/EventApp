package uz.gita.mirzabek.example.eventapp.navigation

import androidx.navigation.NavController
import kotlinx.coroutines.flow.SharedFlow

typealias NavArgs = NavController.() -> Unit

interface NavigationHandler {

    val navStack: SharedFlow<NavArgs>

}