package com.shesha.projects.cmsapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class TravellerDatabaseInstance(@PrimaryKey(autoGenerate = true) @ColumnInfo(name="id") val travelerId: Int?, val travelerName: String, val numTrips: Int ) {
    @Ignore
    constructor(travelerName : String, numTrips : Int) : this(null,travelerName,numTrips)
}