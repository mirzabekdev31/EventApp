package uz.gita.mirzabek.example.eventapp.domain.repository

import uz.gita.mirzabek.example.eventapp.data.room.entities.EventModel
import kotlinx.coroutines.flow.Flow

interface AppRepository {

    fun insertEvent(eventModel: EventModel)
    fun updateEvent(eventModel: EventModel)
    fun getAllEvents():Flow<List<EventModel>>
}