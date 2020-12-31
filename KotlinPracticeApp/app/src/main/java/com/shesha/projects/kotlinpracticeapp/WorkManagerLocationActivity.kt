package com.shesha.projects.kotlinpracticeapp

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.shesha.projects.kotlinpracticeapp.workers.LocationWorker
import java.util.concurrent.TimeUnit

class WorkManagerLocationActivity : AppCompatActivity() {
    private final val requestcode : Int = 1
    lateinit var workManager : WorkManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_manager_location)
        if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_COARSE_LOCATION)!=
            PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION,android.Manifest.permission.ACCESS_COARSE_LOCATION)
                ,requestcode)
        }

        var startTracking : Button = findViewById(R.id.wm_start_tracking_btn)
        var stopTracking : Button = findViewById(R.id.wm_stop_tracking_btn)
        startTracking.setOnClickListener {
            setPeriodicWorkRequest()
        }
        stopTracking.setOnClickListener {
            Log.d("STOP_SERVICE","Clicked")
            stopTrackingUser()
        }
    }

    private fun stopTrackingUser()
    {
        WorkManager.getInstance().cancelAllWorkByTag("LOCATION")
    }

    private fun setPeriodicWorkRequest()
    {
        val periodicWorkRequest : PeriodicWorkRequest = PeriodicWorkRequestBuilder<LocationWorker>(1,TimeUnit.MINUTES).addTag("LOCATION").build()
        workManager = WorkManager.getInstance(applicationContext)
        workManager.enqueueUniquePeriodicWork("location",ExistingPeriodicWorkPolicy.REPLACE,periodicWorkRequest)

    }
}