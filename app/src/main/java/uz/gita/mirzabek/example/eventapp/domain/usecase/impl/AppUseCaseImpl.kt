package uz.gita.mirzabek.example.eventapp.domain.usecase.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.mirzabek.example.eventapp.data.room.entities.EventModel
import uz.gita.mirzabek.example.eventapp.domain.repository.AppRepository
import uz.gita.mirzabek.example.eventapp.domain.usecase.AppUseCase
import javax.inject.Inject

class AppUseCaseImpl @Inject constructor(
    val repository: AppRepository
) :AppUseCase{
    override fun insertEvent(eventModel: EventModel) {
        repository.insertEvent(eventModel)
    }

    override fun updateEvent(eventModel: EventModel) {
        repository.updateEvent(eventModel)
    }

    override fun getAllEvents(): Flow<List<EventModel>> =repository.getAllEvents()
}