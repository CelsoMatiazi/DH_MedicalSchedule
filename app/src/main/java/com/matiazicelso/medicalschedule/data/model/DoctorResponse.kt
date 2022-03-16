package com.matiazicelso.medicalschedule.data.model

data class DoctorResponse(val doctors: List<DoctorItem>, val limit_page: Int)

data class DoctorItem(
    val id: String,
    val name: String,
    val photo: String,
    val specialization: String,
    val classification: Double,
    val views: Int,
)