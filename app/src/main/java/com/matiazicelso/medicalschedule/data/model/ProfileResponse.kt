package com.matiazicelso.medicalschedule.data.model

data class ProfileResponse(val results: List<ProfileItem>)
data class ProfileItem(
    val name: ProfileName,
    val phone: String,
    val dob: ProfileRegistered,
    val location: ProfileLocation,
    val picture: ProfileImage
)

data class ProfileName(val first: String, val last: String)
data class ProfileRegistered(val date: String)
data class ProfileLocation(val city: String)
data class ProfileImage(val large: String)