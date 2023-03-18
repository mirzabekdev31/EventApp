package uz.gita.mirzabek.example.eventapp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.gita.mirzabek.example.eventapp.data.room.entities.EventModel
import uz.gita.mirzabek.example.eventapp.data.room.daos.EventDao

@Database(entities = [EventModel::class], version = 1)
abstract class AppDatabase :RoomDatabase(){
    abstract fun eventDao():EventDao
}