package com.matiazicelso.medicalschedule.data.repository

class RequestApi {

    private val mockEmail = "teste@gmail.com"
    private val mockPassword = "123456"

    fun login(email: String, password:String): Boolean{
        return email == mockEmail && password == mockPassword
    }
}