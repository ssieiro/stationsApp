package io.soniasieiro.stationsapp.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import io.soniasieiro.stationsapp.R
import io.soniasieiro.stationsapp.datamodels.Station
import io.soniasieiro.stationsapp.utils.CustomViewModelFactory
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


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    private fun getStations() {
        this.stations = mViewModel.getStations() as MutableList<Station>
        mAdapter = MainAdapter(requireActivity().applicationContext, this, this, stations)
        stationList.adapter = mAdapter
    }


    override fun onItemClick(station: Station) {
        TODO("Not yet implemented")
    }

    override fun onItemDeleted(station: Station) {
        TODO("Not yet implemented")
    }

}