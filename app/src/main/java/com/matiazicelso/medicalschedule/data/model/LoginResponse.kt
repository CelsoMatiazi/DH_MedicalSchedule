package com.matiazicelso.medicalschedule.data.model

data class LoginSession(
    val user: User,
    val token: String
)

data class User(
    val name: String,
    val email: String,
    val photo: String,
    val phone: String,
    val bday: String,
    val location: String,
)
