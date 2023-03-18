package uz.gita.mirzabek.example.eventapp.data.room.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import uz.gita.mirzabek.example.eventapp.data.room.entities.EventModel

@Dao
interface EventDao {

    @Insert
    fun insertEvent(eventModel: EventModel)

    @Update
    fun updateEvent(eventModel: EventModel)

    @Query("SELECT*FROM event")
    fun getAllEvents(): Flow<List<EventModel>>

    @Query("SELECT*FROM event WHERE id=:id")
    suspend fun getEventById(id: Int): EventModel

}