package uz.gita.mirzabek.example.eventapp.presenter.viewmodels

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import uz.gita.mirzabek.example.eventapp.data.room.entities.EventModel


interface EventScreenViewModel {

    val allEvents:SharedFlow<List<EventModel>>

    fun clickEvent(eventModel: EventModel)

    fun getAllEvents()
}