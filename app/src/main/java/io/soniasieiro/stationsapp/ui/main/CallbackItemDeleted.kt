package io.soniasieiro.stationsapp.ui.main

import io.soniasieiro.stationsapp.datamodels.Station

interface CallbackItemDeleted {
    fun onItemDeleted(station: Station)
}