package io.soniasieiro.stationsapp.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import io.soniasieiro.stationsapp.R
import io.soniasieiro.stationsapp.datamodels.Station
import io.soniasieiro.stationsapp.utils.CustomViewModelFactory
import io.soniasieiro.stationsapp.utils.Utils
import kotlinx.android.synthetic.main.main_fragment.*


class MainFragment : Fragment(), CallbackItemClick, CallbackItemDeleted {

    companion object {
        fun newInstance() = MainFragment()
        const val TAG = "MainFragment"
    }

    private var mAdapter: MainAdapter? = null
    private lateinit var stations: MutableList<Station>

    private val mViewModel: MainViewModel by lazy {
        val factory = CustomViewModelFactory(requireActivity().application)
        ViewModelProvider(this, factory).get(MainViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        getStations()
    }

    private fun init() {
        stationList.layoutManager = LinearLayoutManager(activity)
        stationList.isNestedScrollingEnabled = false
        stationList.setHasFixedSize(false)
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    private fun getStations() {
        var firstTime = Utils.getDefaultsPreference("fistTime", this.context);
        if (firstTime == null) {
            mViewModel.chargeStations();
            Utils.setDefaultsPreference("fistTime", "true", this.context)
        }

        mViewModel.getStations().observe(viewLifecycleOwner, Observer { stations ->
            mAdapter = MainAdapter(requireActivity().applicationContext, this, this, stations)
            stationList.adapter = mAdapter
        })
    }


    override fun onItemClick(station: Station) {
        val gmmIntentUri = Uri.parse("geo:" + station.lat + "," + station.lon + "?q=" + station.lat + "," + station.lon + "(" + station.name + ")")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
       /* val labelLocation = station.name
        val uri =
            "geo:<" + station.lat + ">,<" + station.lon + ">?q=<" + station.lat + ">,<" + station.lon + ">(" + labelLocation + ")"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
        startActivity(intent)*/
    }

    override fun onItemDeleted(station: Station) {
        TODO("Not yet implemented")
    }

}