package com.matiazicelso.medicalschedule.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.matiazicelso.medicalschedule.data.local.dao.DoctorDao
import com.matiazicelso.medicalschedule.data.local.entity.DoctorEntity


@Database(
    entities = [DoctorEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun doctorDao(): DoctorDao
}