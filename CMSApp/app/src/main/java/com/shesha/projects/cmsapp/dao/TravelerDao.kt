package com.shesha.projects.cmsapp.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shesha.projects.cmsapp.model.Traveller
import com.shesha.projects.cmsapp.model.TravellerDatabaseInstance
import kotlinx.coroutines.flow.Flow

@Dao
interface TravelerDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addAllTravelers(travellers : List<TravellerDatabaseInstance>)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addTraveler(traveller: TravellerDatabaseInstance)

    @Query("SELECT * FROM TravellerDatabaseInstance")
    fun getTravellers() : Flow<List<TravellerDatabaseInstance>>
}