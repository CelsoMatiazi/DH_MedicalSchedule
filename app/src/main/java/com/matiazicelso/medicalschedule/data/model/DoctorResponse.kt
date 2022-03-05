package com.matiazicelso.medicalschedule.data.model

data class DoctorResponse(val doctors: List<DoctorItem>)

data class DoctorItem(
    val name: String,
    val photo: String,
    val specialization: String,
    val classification: Double,
    val views: Int
)