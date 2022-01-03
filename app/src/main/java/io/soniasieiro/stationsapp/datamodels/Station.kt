package io.soniasieiro.stationsapp.datamodels

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

@Entity(tableName = "stations")
data class Station(

    @PrimaryKey
    var id: String = UUID.randomUUID().toString(),

    @field:SerializedName("name")
    val name: String? = "",

    @field:SerializedName("lat")
    val lat: String? = "",

    @field:SerializedName("lon")
    val lom: String? = "",

    @field:SerializedName("adress")
    val adress: String? = "",

) : Serializable
