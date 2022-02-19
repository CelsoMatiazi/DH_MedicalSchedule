package com.matiazicelso.medicalschedule.data.model

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime


@RequiresApi(Build.VERSION_CODES.O)
class UserProfile( user: ProfileItem ){

    val name = "${user.name.first} ${user.name.last}"
    val phone = user.phone
    val dob = convertDate(user.dob.date)
    val location = user.location.city
    val image = user.picture.large
}

@RequiresApi(Build.VERSION_CODES.O)
private fun convertDate(date: String): String{
    val newDate = date.dropLast(1)
    val d = LocalDateTime.parse(newDate)
    return "${d.dayOfMonth}/${d.monthValue}/${d.year}"
}