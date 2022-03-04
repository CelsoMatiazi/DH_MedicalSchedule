package com.matiazicelso.medicalschedule.data.model

data class DoctorResponse(val results: List<DoctorItem>)

data class DoctorItem(
    val name: String,
    val photo: String,
    val specialization: ProfileRegistered,
    val classification: Double,
    val views: Int
)