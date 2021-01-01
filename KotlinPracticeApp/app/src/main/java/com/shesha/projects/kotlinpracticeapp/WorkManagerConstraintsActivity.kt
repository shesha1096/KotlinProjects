package com.shesha.projects.kotlinpracticeapp

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.work.*
import com.shesha.projects.kotlinpracticeapp.services.LocationService
import com.shesha.projects.kotlinpracticeapp.workers.LocationWorker
import java.util.*
import java.util.concurrent.TimeUnit

class WorkManagerConstraintsActivity : AppCompatActivity() {
    private final val requestcode : Int = 1
    var streetAddress : String = ""
    lateinit var workManager : WorkManager
    lateinit var mainHandler: Handler
    lateinit var resultTextView : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_manager_constraints)
        if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_COARSE_LOCATION)!=
            PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION,android.Manifest.permission.ACCESS_COARSE_LOCATION)
                ,requestcode)
        }
        mainHandler = Handler(Looper.getMainLooper())
        var startTracking : Button = findViewById(R.id.wm_con_start_tracking_btn)
        var stopTracking : Button = findViewById(R.id.wm_con_stop_tracking_btn)
        resultTextView = findViewById(R.id.resultTextView)

        startTracking.setOnClickListener {
            startTrackingUser()
            setPeriodicWorkRequest()
        }
        stopTracking.setOnClickListener {
            Log.d("STOP_SERVICE","Clicked")
            stopTrackingUser()
        }
    }

    private fun startTrackingUser()
    {
        mainHandler.post(updateUserLocationTask)
    }

    private val updateUserLocationTask = object : Runnable {
        override fun run() {
            setPeriodicWorkRequest()
            mainHandler.postDelayed(this, 60000)
        }
    }


    private fun stopTrackingUser()
    {
        mainHandler.removeCallbacks(updateUserLocationTask)
    }

    private fun setPeriodicWorkRequest()
    {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresCharging(true)
            .build()

        val periodicWorkRequest : OneTimeWorkRequest = OneTimeWorkRequestBuilder<LocationWorker>()
            .setConstraints(constraints)
            .addTag("LOCATION").build()
        workManager = WorkManager.getInstance(applicationContext)
        workManager.enqueue(periodicWorkRequest)
        workManager.getWorkInfoByIdLiveData(periodicWorkRequest.id)
            .observe(this, Observer { workInfo: WorkInfo? ->
                if (workInfo != null) {
                    Log.d("STATE",workInfo.state.toString())
                    // Do something with progress information
                    if (workInfo.state == WorkInfo.State.ENQUEUED)
                    {
                        resultTextView.text = "No Internet Connection, or phone is not charging"
                    }
                    else{
                        val sharedPref = getSharedPreferences(getString(R.string.geopoints),Context.MODE_PRIVATE)
                        val defaultValue = "latitude"
                        val latitude = sharedPref.getString(getString(R.string.latitude),defaultValue)
                        val longitude = sharedPref.getString(getString(R.string.longitude),defaultValue)
                        var geocoder : Geocoder
                        var addresses : List<Address> = arrayListOf()
                        geocoder = Geocoder(this, Locale.getDefault())
                        if (latitude != null) {
                            if (longitude != null) {
                                addresses = geocoder.getFromLocation(latitude.toDouble(),longitude.toDouble(),1)
                                resultTextView.text = addresses.get(0).getAddressLine(0)
                            }
                        }
                        streetAddress = addresses.get(0).getAddressLine(0)
                    }

                }
            })

    }
}