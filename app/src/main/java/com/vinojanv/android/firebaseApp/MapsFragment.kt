package com.vinojanv.android.firebaseApp

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment : Fragment() {

    private val callback = OnMapReadyCallback { googleMap ->

        val clientName = arguments?.getString("name")
        val clientLat = arguments?.getFloat("lat")
        val clientLng = arguments?.getFloat("lng")

        val location = LatLng(clientLat!!.toDouble(), clientLng!!.toDouble())
        googleMap.addMarker(MarkerOptions().position(location).title(clientName))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(location))
        googleMap.setMinZoomPreference(10f)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //change action bar title
        (activity as AppCompatActivity).supportActionBar?.title = "Locate " +
                arguments?.getString("name")!!.split(" ")[0]

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}