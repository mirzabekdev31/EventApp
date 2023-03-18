package uz.gita.mirzabek.example.eventapp.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import uz.gita.mirzabek.example.eventapp.R
import uz.gita.mirzabek.example.eventapp.data.room.AppDatabase
import uz.gita.mirzabek.example.eventapp.data.room.daos.EventDao
import uz.gita.mirzabek.example.eventapp.data.room.entities.EventModel
import javax.inject.Singleton


@DelicateCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @[Provides Singleton]
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "event.db")
            .addCallback(object : RoomDatabase.Callback(){
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    val list = listOf(
                        "Screen on",
                        "Screen off",
                        "Wifi connected",
                        "Wifi disconnected",
                        "Bluetooth on",
                        "Bluetooth off",
                        "HeadPhones on",
                        "HeadPhones off",
                        "Plane on",
                        "Plane off",
                        "Changed time",
                        "Shut down",
                        "Full battery",
                        "Low battery",
                        "Power on",
                        "Power off"
                    )
                    val dao= provideEventDao(provideDatabase(context))
                    GlobalScope.launch {
                        list.forEach {
                            dao.insertEvent(EventModel(0, R.drawable.ic_baseline_full,it,0))
                        }
                    }
                }
            }).build()
    }

    @[Provides Singleton]
    fun provideEventDao(appDatabase: AppDatabase): EventDao = appDatabase.eventDao()
}