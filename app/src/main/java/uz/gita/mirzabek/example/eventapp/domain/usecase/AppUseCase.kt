package uz.gita.mirzabek.example.eventapp.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.mirzabek.example.eventapp.data.room.entities.EventModel

interface AppUseCase {

    fun insertEvent(eventModel: EventModel)

    fun updateEvent(eventModel: EventModel)

    fun getAllEvents():Flow<List<EventModel>>
}