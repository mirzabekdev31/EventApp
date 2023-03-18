package uz.gita.mirzabek.example.eventapp.domain.repository.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.mirzabek.example.eventapp.domain.repository.AppRepository
import uz.gita.mirzabek.example.eventapp.data.room.daos.EventDao
import uz.gita.mirzabek.example.eventapp.data.room.entities.EventModel
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor (
    private val eventDao:EventDao
        ): AppRepository {
    override fun insertEvent(eventModel: EventModel) {
        eventDao.insertEvent(eventModel)
    }

    override fun updateEvent(eventModel: EventModel) {
        eventDao.updateEvent(eventModel)
    }

    override fun getAllEvents(): Flow<List<EventModel>> =eventDao.getAllEvents()


}