package com.matiazicelso.medicalschedule.data.local

import android.content.ContentValues
import android.content.Context
import android.provider.BaseColumns
import com.matiazicelso.medicalschedule.data.local.entity.DoctorEntry
import com.matiazicelso.medicalschedule.data.local.entity.DoctorEntry.COLUMN_NAME_API_ID
import com.matiazicelso.medicalschedule.data.local.entity.DoctorEntry.COLUMN_NAME_CLASSIFICATION
import com.matiazicelso.medicalschedule.data.local.entity.DoctorEntry.COLUMN_NAME_NAME
import com.matiazicelso.medicalschedule.data.local.entity.DoctorEntry.COLUMN_NAME_PHOTO
import com.matiazicelso.medicalschedule.data.local.entity.DoctorEntry.COLUMN_NAME_SPECIALIZATION
import com.matiazicelso.medicalschedule.data.local.entity.DoctorEntry.COLUMN_NAME_VIEWS
import com.matiazicelso.medicalschedule.data.model.DoctorItem

class DbDoctorHelper(context: Context) {

    private val db = DbHelper(context, sqlCreationEntries, sqlDeleteEntries)


    fun insert(values: ContentValues.() -> Unit): Long{
        return db.writableDatabase.insert(
            DoctorEntry.TABLE_NAME,
            null,
            ContentValues().apply(values)
        )
    }

    fun update(id: Long, values: ContentValues.() -> Unit){
        db.writableDatabase.update(
            DoctorEntry.TABLE_NAME,
            ContentValues().apply(values),
            "WHERE id = ?",
            arrayOf(id.toString())
        )
    }

    fun delete(id: Long){
        db.writableDatabase.delete(
            DoctorEntry.TABLE_NAME,
            "${BaseColumns._ID} = ?",
            arrayOf(id.toString())
        )
    }


    fun listAllWithId(id_api: String): MutableList<DoctorItem>{
        val projection = arrayOf(
            BaseColumns._ID,
            COLUMN_NAME_NAME,
            COLUMN_NAME_PHOTO,
            COLUMN_NAME_SPECIALIZATION,
            COLUMN_NAME_CLASSIFICATION,
            COLUMN_NAME_VIEWS,
            COLUMN_NAME_API_ID
        )

        val selection = "$COLUMN_NAME_API_ID = ?"
        val selectionValue = arrayOf(id_api)

        val order = "${BaseColumns._ID}"

        val doctorList = mutableListOf<DoctorItem>()

        val cursor = db.readableDatabase.query(
            DoctorEntry.TABLE_NAME,
            projection,
            selection,
            selectionValue,
            null,
            null,
            order
        )

        with(cursor){
            while (moveToNext()){
                val id = getLong(getColumnIndexOrThrow(BaseColumns._ID))
                val name = getString(getColumnIndexOrThrow(COLUMN_NAME_NAME))
                val photo = getString(getColumnIndexOrThrow(COLUMN_NAME_PHOTO))
                val specialization = getString(getColumnIndexOrThrow(COLUMN_NAME_SPECIALIZATION))
                val classification = getString(getColumnIndexOrThrow(COLUMN_NAME_CLASSIFICATION))
                val views = getString(getColumnIndexOrThrow(COLUMN_NAME_VIEWS))
                val id_api = getString(getColumnIndexOrThrow(COLUMN_NAME_API_ID))

                println("$id | $name | $photo")

                doctorList.add(
                    DoctorItem(
                        id_api,
                        name,
                        photo,
                        specialization,
                        classification.toDouble(),
                        views.toInt()
                    )
                )
            }
        }

        cursor.close()
        return doctorList

    }


    companion object{
        private const val sqlCreationEntries = "CREATE TABLE ${DoctorEntry.TABLE_NAME}(" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "${DoctorEntry.COLUMN_NAME_NAME} TEXT," +
                "${DoctorEntry.COLUMN_NAME_PHOTO} TEXT," +
                "${DoctorEntry.COLUMN_NAME_SPECIALIZATION} TEXT," +
                "${DoctorEntry.COLUMN_NAME_CLASSIFICATION} TEXT," +
                "${DoctorEntry.COLUMN_NAME_EXPERIENCE} TEXT," +
                "${DoctorEntry.COLUMN_NAME_PATIENT_STORIES} TEXT," +
                "${DoctorEntry.COLUMN_NAME_VIEWS} TEXT," +
                "${DoctorEntry.COLUMN_NAME_API_ID} TEXT)"

        private const val sqlDeleteEntries = "DROP TABLE IF EXISTS ${DoctorEntry.TABLE_NAME}"

    }

}