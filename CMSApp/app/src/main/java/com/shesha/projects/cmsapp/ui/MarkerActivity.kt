package com.shesha.projects.cmsapp.ui

import android.graphics.Color
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.data.geojson.GeoJsonLayer
import com.google.maps.android.data.geojson.GeoJsonPolygonStyle
import com.shesha.projects.cmsapp.R
import org.json.JSONException
import java.io.IOException
import java.util.*


class MarkerActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private var allPoints : MutableList<LatLng> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marker)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap?) {
        var layer : GeoJsonLayer ?= null
        var style : GeoJsonPolygonStyle

        if (googleMap != null) {
            mMap = googleMap
        }
        var bounds : LatLngBounds = googleMap!!.projection.visibleRegion.latLngBounds

        // Add a marker in Sydney and move the camera
        val tampa = LatLng(27.9947147, -82.5943639)
        mMap.addMarker(
            MarkerOptions()
            .position(tampa)
            .title("Marker in Tampa Florida"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(tampa))
        allPoints.add(tampa)

        mMap.setOnMapClickListener {
            layer?.removeLayerFromMap()
            val geocoder: Geocoder
            val addresses: List<Address>
            geocoder = Geocoder(this, Locale.getDefault())

            addresses = geocoder.getFromLocation(
                it.latitude,
                it.longitude,
                1
            ) // Here 1 represent max location result to returned, by documents it recommended 1 to 5


            val address: String = addresses[0]
                .getAddressLine(0) // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()

            val city: String = addresses[0].getLocality()
            val state: String = addresses[0].getAdminArea()
            val country: String = addresses[0].getCountryName()
            Log.d("COUNTRY",country)
            if (country != "United States")
            {
                Toast.makeText(this,"Cannot add country outside of USA",Toast.LENGTH_SHORT).show()
            }
            else{
                allPoints.add(it)
                mMap.clear()
                mMap.addMarker(MarkerOptions().position(it))
                try {
                    layer = GeoJsonLayer(mMap, R.raw.get_geojson, applicationContext)
                    style = layer!!.defaultPolygonStyle
                    style.strokeColor = Color.MAGENTA
                    style.strokeWidth = 5f
                    layer!!.addLayerToMap()
                    mMap.setLatLngBoundsForCameraTarget(layer!!.boundingBox)


                } catch (ex: IOException) {
                    Log.e("IOException", ex.getLocalizedMessage())
                } catch (ex: JSONException) {
                    Log.e("JSONException", ex.localizedMessage)
                }


            }


        }
    }
}