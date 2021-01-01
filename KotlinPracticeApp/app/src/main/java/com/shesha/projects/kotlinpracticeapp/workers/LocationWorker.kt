package com.shesha.projects.kotlinpracticeapp.workers

import android.Manifest
import android.app.ActivityManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Looper
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.shesha.projects.kotlinpracticeapp.R
import com.shesha.projects.kotlinpracticeapp.services.LocationForegroundService
import com.shesha.projects.kotlinpracticeapp.services.LocationService
import java.lang.Exception

class LocationWorker(context : Context, params: WorkerParameters) : Worker(context,params)
{
    var context : Context = context
    var latitude : Double = 0.0
    var longitude : Double = 0.0
    var locationCallback: LocationCallback? = object : LocationCallback() {
        override fun onLocationResult(p0: LocationResult?) {
            super.onLocationResult(p0)
            if (p0 != null && p0.lastLocation != null)
            {
                latitude = p0.lastLocation.latitude
                longitude = p0.lastLocation.longitude
                val sharedPref = context.getSharedPreferences(context.getString(R.string.geopoints), Context.MODE_PRIVATE) ?: return
                with (sharedPref.edit())
                {
                    putString(context.getString(R.string.latitude),latitude.toString())
                    putString(context.getString(R.string.longitude),longitude.toString())
                    apply()
                }
                Log.d("LATITUDE",latitude.toString())
                Log.d("LONGITUDE",longitude.toString())
            }
        }


    }


    override fun doWork(): Result {
        try {
            var channelId : String = "location_notification_channel"
            var notificationManager : NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
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
                    var notificationChannel = NotificationChannel(channelId,"Location Service",
                        NotificationManager.IMPORTANCE_HIGH)
                    notificationChannel.description = "This channel is being used by location services"
                    notificationManager.createNotificationChannel(notificationChannel)
                }
            }
            var locationRequest  = LocationRequest()
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)


            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    context,
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

            }
            LocationServices.getFusedLocationProviderClient(context)
                .requestLocationUpdates(locationRequest,locationCallback, Looper.getMainLooper())

            var outputData : Data = Data.Builder().putString("Latitude",latitude.toString()).build()
            //Log.d("LATITUDE",outputData.toString())
            return Result.success()

        }

        catch(e: Exception)
        {
            return Result.failure()
        }

    }


}