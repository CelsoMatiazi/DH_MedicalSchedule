package com.matiazicelso.medicalschedule.data.factory

import android.content.Context
import androidx.room.Room
import com.matiazicelso.medicalschedule.data.local.AppDatabase
import java.lang.IllegalStateException

object DatabaseFactory {

    private var instance: AppDatabase? = null

    fun getDatabase() = instance ?: throw IllegalStateException("Database is not initialized")

    fun build(context: Context): AppDatabase{
        val database = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "doctor-room"
        )

        database.allowMainThreadQueries()
        val dbInstance = database.build()
        instance = dbInstance

        return dbInstance

    }

    fun removeInstance(){
        instance = null
    }
}