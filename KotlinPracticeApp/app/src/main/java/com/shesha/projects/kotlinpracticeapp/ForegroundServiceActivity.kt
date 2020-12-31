package com.shesha.projects.kotlinpracticeapp

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.ActivityCompat
import com.shesha.projects.kotlinpracticeapp.services.LocationForegroundService

class ForegroundServiceActivity : AppCompatActivity() {
    private final val requestcode : Int = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foreground_service)
        if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_COARSE_LOCATION)!=
            PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION,android.Manifest.permission.ACCESS_COARSE_LOCATION)
                ,requestcode)
        }

        var startTracking : Button = findViewById(R.id.start_tracking_btn)
        var stopTracking : Button = findViewById(R.id.stop_tracking_btn)
        startTracking.setOnClickListener {
            startLocationService()
        }
        stopTracking.setOnClickListener {
            stopLocationService()
        }
    }

    private fun isLocationServiceRunning() : Boolean{
        var activityManager : ActivityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        if (activityManager != null)
        {
            for (activityManager in activityManager.getRunningServices(Int.MAX_VALUE))
            {
                if (LocationForegroundService::class.java.name.equals(activityManager.service.className))
                {
                    if (activityManager.foreground)
                    {
                        return true
                    }
                }
            }
            return false
        }
        return false
    }

    private fun startLocationService(){
        if (!isLocationServiceRunning())
        {
            var intent : Intent = Intent(applicationContext,LocationForegroundService::class.java)
            intent.setAction("startLocationService")
            startService(intent)
        }
    }

    private fun stopLocationService(){
        if (isLocationServiceRunning())
        {
            var intent : Intent = Intent(applicationContext,LocationForegroundService::class.java)
            intent.setAction("stopLocationService")
            startService(intent)
        }
    }

}