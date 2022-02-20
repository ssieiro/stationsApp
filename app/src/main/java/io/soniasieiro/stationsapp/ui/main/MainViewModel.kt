package io.soniasieiro.stationsapp.ui.main

import android.app.Application
import android.preference.PreferenceManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import io.soniasieiro.stationsapp.datamodels.Station
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import io.soniasieiro.stationsapp.db.StationRoomDatabase
import java.util.*

class MainViewModel(private val context: Application) : ViewModel() {

    fun chargeStations() {
        val csvData: String =
                "Diego de León ,\"40.435405,-3.675118\",\"calle Francisco silvela, 60\"\n" +
                        "Diego de León ,\"40.435556,-3.675297\",calle Francisco silvela 66 esq calle mejico\n" +
                        "Avenida de América ,\"40.436128,-3.676325\",\"calle Francisco silvela, 73\"\n" +
                        "Avenida de América ,\"40.437831,-3.678127\",calle Francisco silvela 89 esq calle maria de molina\n" +
                        "Avenida de América ,\"40.43827,-3.678248\",calle Francisco silvela 92 esq calle san Fernando de jarama. paso elevado\n" +
                        "Prosperidad ,\"40.441609,-3.678422\",calle lopez de hoyos 66 cruce con principe de vergara. isleta\n" +
                        "Prosperidad ,\"40.443195,-3.675244\",calle general zabala esq lopez de hoyos\n" +
                        "Prosperidad ,\"40.443889,-3.67491\",calle lopez de hoyos 73 esq calle suero de quiñones\n" +
                        "Alfonso XIII,\"40.446347,-3.67063\",lopez de hoyos esq santa hortensia\n" +
                        "Alfonso XIII,\"40.448272,-3.667774\",\"clara del rey esq lopez de hoyos, frente acceso Alfonso xiii\"\n" +
                        "Alfonso XIII,\"40.448868,-3.667173\",gustavo fdez balbuena esq lopez de hoyos\n" +
                        "Alfonso XIII,\"40.448329,-3.666299\",corazon de maria 67\n" +
                        "Alfonso XIII,\"40.448966,-3.66651\",corazon de maria esq lopez de hoyos\n" +
                        "Avenida de la paz,\"40.450419,-3.665073\",padre claret esq lopez de hoyos\n" +
                        "Avenida de la paz,\"40.452393,-3.662812\",m30 en mediana central a la altura del puente de ramon y cajal\n" +
                        "Avenida de la paz,\"40.454887,-3.658464\",jose silva 14\n" +
                        "Avenida de la paz estacion,\"40.452762,-3.662881\",m30 en mediana central a la altura del puente de ramon y cajal\n" +
                        "Arturo Soria ,\"40.456634,-3.65495\",ulises 5-7 posterior\n" +
                        "Arturo Soria ,\"40.456779,-3.654156\",asura esq ulises\n" +
                        "Arturo Soria ,\"40.458518,-3.651388\",puerto de santa maria 59 esq ulises\n" +
                        "Esperanza,\"40.459254,-3.646844\",palermo 50\n" +
                        "Esperanza,\"40.459009,-3.645772\",palermo 58 esq andorra\n" +
                        "Esperanza,\"40.458877,-3.643183\",silvano frente al liceo frances\n" +
                        "Canillas,\"40.461256,-3.6371\",el algabeño 26\n" +
                        "Canillas,\"40.464005,-3.635322\",av machupichu junto acceso principal\n" +
                        "Canillas,\"40.464589,-3.635499\",av machupichu junto acceso montalvos\n" +
                        "Mar de cristal,\"40.467156,-3.637189\",emigrantes esq tribaldos\n" +
                        "Mar de cristal,\"40.46928,-3.637993\",\"glorieta mar de cristal, isleta central\"\n" +
                        "Mar de cristal,\"40.469431,-3.63831\",glorieta mar de cristal junto lucernario\n" +
                        "Mar de cristal,\"40.470849,-3.639032\",mar adriatico frente acceso ayacucho\n" +
                        "Mar de cristal,\"40.470992,-3.639616\",mar adriatico frente c/ chaparral\n" +
                        "San lorenzo,\"40.473134,-3.639737\",av de barranquilla frente bloque 36\n" +
                        "San lorenzo,\"40.474136,-3.639562\",av de barranquilla frente bloque 1\n" +
                        "San lorenzo,\"40.47584,-3.640404\",\"mar de  coral, parque infantil junto colegio femenino\"\n" +
                        "San lorenzo,\"40.476962,-3.644189\",santa virgilia 8\n" +
                        "parque santa maria,\"40.477199,-3.645131\",los arcos esq santa virgilia\n" +
                        "parque santa maria,\"40.479867,-3.650133\",zona de campo entre c/ santa adela y acceso m40\n" +
                        "parque santa maria,\"40.476523,-3.648486\",entre abarzuza y mar de las antillas en zona de aparcamientos \n" +
                        "hortaleza,\"40.47504,-3.651525\",parque entre c/acebedo y av de la virgen del carmen\n" +
                        "hortaleza,\"40.474748,-3.652257\",capitan cortes esq av de la virgen del carmen\n" +
                        "hortaleza,\"40.47431,-3.659376\",frente a la plaza de los templarios 10 en zona de parque\n" +
                        "manoteras,\"40.47555,-3.661334\",bacares entre velez rubio y cuevas de almanzora a la altura de la salida de emergencias \n" +
                        "manoteras,\"40.476464,-3.662439\",bacares entre velez rubio y cuevas de almanzora\n" +
                        "pinar de chamartin,\"40.476884,-3.663035\",bacares a la altura del templete de acceso\n" +
                        "pinar de chamartin,\"40.478476,-3.66539\",dalia 14\n" +
                        "pinar de chamartin,\"40.480467,-3.667857\",caleruega esq condado de treviño"

        val rows: List<List<String>> = csvReader().readAll(csvData)
        for (row in rows) {
            var latLong = row[1].split(',')
            println(latLong)
            UUID.randomUUID().toString()
            var station = Station(UUID.randomUUID().toString(), row[0], latLong[0], latLong[1], row[2])

            StationRoomDatabase.getInstance(context).stationDao().insertStation(station)
            println(station)
        }

    }

    fun getStations() : LiveData<List<Station>> {

        var stations = StationRoomDatabase.getInstance(context).stationDao().getAllStations()

        return stations
    }



}