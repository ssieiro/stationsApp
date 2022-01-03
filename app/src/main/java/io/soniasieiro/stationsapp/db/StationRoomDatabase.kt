package io.soniasieiro.stationsapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import io.soniasieiro.stationsapp.datamodels.Station

@Database(entities = [Station::class], version = 1, exportSchema = false)
abstract class StationRoomDatabase: RoomDatabase() {
    abstract fun stationDao(): StationDao

    companion object {

        private var instance: StationRoomDatabase? = null

        fun getInstance(context: Context): StationRoomDatabase {

            if (instance == null) {

                synchronized(StationRoomDatabase::class) {
                    instance = Room.databaseBuilder(context.applicationContext, StationRoomDatabase::class.java, "stations_db")
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }

            return instance!!
        }
    }
}