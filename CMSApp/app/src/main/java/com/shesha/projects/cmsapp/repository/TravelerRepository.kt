package com.shesha.projects.cmsapp.repository

import androidx.annotation.WorkerThread
import com.shesha.projects.cmsapp.dao.EmployeeDao
import com.shesha.projects.cmsapp.dao.TravelerDao
import com.shesha.projects.cmsapp.model.TravellerDatabaseInstance
import kotlinx.coroutines.flow.Flow

class TravelerRepository private constructor(private val travelerDao : TravelerDao){

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun addTraveler(travellerDatabaseInstance: TravellerDatabaseInstance)
    {
        travelerDao.addTraveler(travellerDatabaseInstance)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun addAllTravelers(travellers : List<TravellerDatabaseInstance>)
    {
        travelerDao.addAllTravelers(travellers)
    }

    val allTravellers  : Flow<List<TravellerDatabaseInstance>> = travelerDao.getTravellers()

    companion object
    {
        @Volatile private var instance : TravelerRepository ?= null

        fun getInstance (travelerDao: TravelerDao) = instance ?: synchronized(this)
        {
            instance ?: TravelerRepository(travelerDao).also { instance = it }
        }
    }
}