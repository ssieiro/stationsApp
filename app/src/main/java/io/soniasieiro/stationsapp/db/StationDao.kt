package io.soniasieiro.stationsapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import io.soniasieiro.stationsapp.datamodels.Station

@Dao
abstract class StationDao {

    @Query("SELECT * FROM stations")
    abstract fun getAllStations(): LiveData<List<Station>>

    @Query("SELECT * FROM stations")
    abstract fun getAll(): List<Station>

    @Query("SELECT * FROM stations WHERE id = :stationId")
    abstract fun getStationById(stationId: String) : LiveData<Station>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertStation(station: Station)

    @Delete
    abstract fun deleteStation(station: Station)
}