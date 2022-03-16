package com.matiazicelso.medicalschedule.data.factory

import android.content.Context
import androidx.room.Room
import com.matiazicelso.medicalschedule.data.local.AppDatabase

object DatabaseFactory {

    private var instance: AppDatabase? = null

    fun getDatabase(context: Context) = instance ?: build(context)

    private fun build(context: Context): AppDatabase{
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