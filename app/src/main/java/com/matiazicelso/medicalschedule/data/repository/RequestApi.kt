package com.matiazicelso.medicalschedule.data.repository

import com.google.gson.Gson
import com.matiazicelso.medicalschedule.data.model.UserLogin
import com.matiazicelso.medicalschedule.utils.readJson

class RequestApi {

    private fun getUser() : UserLogin {
        val json = readJson("login")
        return Gson().fromJson(json, UserLogin::class.java) as UserLogin
    }

    fun login(email: String, password:String): Boolean{
        val user : UserLogin = getUser()
        return email == user.email && password == user.password
    }
}