package io.soniasieiro.stationsapp.ui.main

import io.soniasieiro.stationsapp.datamodels.Station

interface CallbackItemClick {
    fun onItemClick(station: Station)
}