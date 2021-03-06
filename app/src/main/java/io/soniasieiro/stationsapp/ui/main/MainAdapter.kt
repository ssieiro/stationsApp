package io.soniasieiro.stationsapp.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.soniasieiro.stationsapp.R
import io.soniasieiro.stationsapp.datamodels.Station
import kotlinx.android.synthetic.main.item_station.view.*
import java.util.*

class MainAdapter(
    private val context: Context,
    private val callbackItemClick: CallbackItemClick,
    private val callbackItemDeleted: CallbackItemDeleted,
    private val stationList: MutableList<Station>?,
    private val originalStations: List<Station>

) : RecyclerView.Adapter<MainAdapter.MainHolder>() {
    class MainHolder(v: View) : RecyclerView.ViewHolder(v) {
        internal var view = v
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_station, parent, false)
        return MainHolder(view)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        stationList?.get(position).let { station ->

            holder.view.cardView.setOnClickListener {
                callbackItemClick.onItemClick(station!!)
            }

            holder.view.cardView.deleteButton.setOnClickListener {
                callbackItemDeleted.onItemDeleted(station!!)
            }

            holder.view.stationName.text = station?.name
            holder.view.stationAdress.text = station?.adress
        }
    }

    override fun getItemCount(): Int {
        return  stationList?.size ?: 0
    }

    fun filter(text: String) {
        val longitud: Int = text.length
        if (longitud == 0) {
            stationList?.clear()
            stationList?.addAll(originalStations)
        } else {
            if (stationList != null) {
                val newStations = stationList.filter { i -> i.name!!.toLowerCase(Locale.ROOT).contains(text.toLowerCase(
                    Locale.ROOT))
                }
                stationList.clear()
                stationList.addAll(newStations)
            }
        }
        notifyDataSetChanged()
    }
}