package io.soniasieiro.stationsapp.ui.main

import android.app.Application
import androidx.lifecycle.ViewModel
import io.soniasieiro.stationsapp.datamodels.Station

class MainViewModel(private val context: Application) : ViewModel() {

    var station1: Station = Station("id", "Estacion del Arte", "2", "1","C/Inventada, 3")
    fun getStations() : List<Station> {
        return listOf(station1,station1,
            station1,station1,station1,station1,station1,station1
            ,station1,station1,station1,station1,station1,station1,station1,station1)
    }
}