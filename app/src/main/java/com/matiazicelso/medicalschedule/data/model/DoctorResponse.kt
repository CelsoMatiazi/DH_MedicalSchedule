package com.matiazicelso.medicalschedule.data.model

import com.matiazicelso.medicalschedule.data.local.entity.DoctorEntity

data class DoctorResponse(val doctors: List<DoctorItem>, val limit_page: Int)

data class DoctorItem(
    val id: String,
    val name: String,
    val photo: String,
    val specialization: String,
    val classification: Double,
    val experience: Int,
    val patientStories: Int,
    val views: Int,
    var isFavorite: Boolean = false
)


fun DoctorItem.toDoctorEntity() = DoctorEntity(
    name = name,
    photo = photo,
    specialization = specialization,
    classification = classification,
    experience = experience,
    patientStories = patientStories,
    views = views,
    apiId = id,

)