package com.shesha.projects.locationtrackingapp

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.os.Looper
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.app.NotificationCompat
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices

class LocationService : Service()
{
    var locationCallback: LocationCallback? = object : LocationCallback() {
        override fun onLocationResult(p0: LocationResult?) {
            super.onLocationResult(p0)
            if (p0 != null && p0.lastLocation != null)
            {
                var latitude : Double = p0.lastLocation.latitude
                var longitude : Double = p0.lastLocation.longitude
                Log.d("LATITUDE",latitude.toString())
                Log.d("LONGITUDE",longitude.toString())
            }
        }


}
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    private fun startLocationService(){
        var channelId : String = "location_notification_channel"
        var notificationManager : NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        var resultIntent : Intent = Intent()
        var pendingIntent : PendingIntent = PendingIntent.getActivity(
            applicationContext,
            0,
            resultIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        var builder = NotificationCompat.Builder(applicationContext,channelId)
        builder.setSmallIcon(R.mipmap.ic_launcher)
        builder.setContentTitle("Tracking Location")
        builder.setDefaults(NotificationCompat.DEFAULT_ALL)
        builder.setContentText("Running")
        builder.setContentIntent(pendingIntent)
        builder.setAutoCancel(false)
        builder.setPriority(NotificationCompat.PRIORITY_MAX)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            if (notificationManager != null
                && notificationManager.getNotificationChannel(channelId) == null)
            {
                var notificationChannel = NotificationChannel(channelId,"Location Service",NotificationManager.IMPORTANCE_HIGH)
                notificationChannel.description = "This channel is being used by location services"
                notificationManager.createNotificationChannel(notificationChannel)
            }
        }
        var locationRequest  = LocationRequest()
        locationRequest.setInterval(60000)
        locationRequest.setFastestInterval(2000)
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        LocationServices.getFusedLocationProviderClient(this)
            .requestLocationUpdates(locationRequest,locationCallback,Looper.getMainLooper())

    }

    private fun stopLocationService()
    {
        LocationServices.getFusedLocationProviderClient(this)
            .removeLocationUpdates(locationCallback)
        stopForeground(true)
        stopSelf()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent != null)
        {
            var action : String = intent.action.toString()
            if (action != null)
            {
                if (action.equals("startLocationService"))
                {
                    startLocationService()
                }
                else if (action.equals("stopLocationService"))
                {
                    stopLocationService()
                }
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }


}