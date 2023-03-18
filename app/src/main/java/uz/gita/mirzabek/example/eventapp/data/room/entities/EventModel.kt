package uz.gita.mirzabek.example.eventapp.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "event")
data class EventModel (
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val image:Int,
    val name:String,
    val isSwitch:Int=0
    ): Serializable