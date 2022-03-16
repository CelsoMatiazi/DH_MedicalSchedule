package com.matiazicelso.medicalschedule.data.local.dao

import androidx.room.*
import com.matiazicelso.medicalschedule.data.local.entity.DoctorEntity


@Dao
interface DoctorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg doctorEntity: DoctorEntity)

    @Delete
    fun delete(doctorEntity: DoctorEntity)

    @Update
    fun update(doctorEntity: DoctorEntity)

    @Query("SELECT * FROM doctor ORDER BY id DESC")
    fun listAll(): List<DoctorEntity>

    @Query("SELECT * FROM doctor WHERE id = :id")
    fun retireveById(id: Int) : DoctorEntity

    @Query("SELECT * FROM doctor WHERE api_id = :apiId")
    fun retrieveByApiId(apiId: String): DoctorEntity

}