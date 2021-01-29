package com.shesha.projects.cmsapp.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.shesha.projects.cmsapp.model.Employee
import com.shesha.projects.cmsapp.model.Project
import com.shesha.projects.cmsapp.model.Traveller
import com.shesha.projects.cmsapp.model.TravellerDatabaseInstance


@Database(
        entities = [Employee::class,Project::class,TravellerDatabaseInstance::class],
        version = 1
)
abstract class EmployeeDatabase : RoomDatabase(){
    abstract fun getEmployeeDao() : EmployeeDao

    abstract fun getProjectDao() : ProjectDao

    abstract fun getTravelerDao() : TravelerDao

    companion object
    {
        @Volatile
        private var instance : EmployeeDatabase ?= null
        private val LOCK = Any()

        operator fun invoke(context : Context) = instance ?: synchronized(LOCK)
        {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(
                            context.applicationContext,
                        EmployeeDatabase::class.java,
                        "EmployeeDatabase.db"
                        ).build()
    }

}