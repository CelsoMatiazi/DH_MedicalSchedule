package com.matiazicelso.medicalschedule.data.local.entity

import android.provider.BaseColumns

object DoctorEntry: BaseColumns {

    const val TABLE_NAME = "doctor"
    const val COLUMN_NAME_NAME = "name"
    const val COLUMN_NAME_PHOTO = "photo"
    const val COLUMN_NAME_SPECIALIZATION = "specialization"
    const val COLUMN_NAME_CLASSIFICATION = "classification"
    const val COLUMN_NAME_EXPERIENCE = "experience"
    const val COLUMN_NAME_PATIENT_STORIES = "stories"
    const val COLUMN_NAME_VIEWS = "views"
    const val COLUMN_NAME_API_ID = "api_id"

}