package com.matiazicelso.medicalschedule.data.repository

import com.matiazicelso.medicalschedule.data.model.LoginResponse
import com.matiazicelso.medicalschedule.data.netWork.ApiLogin
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class LoginRepository(private val apiLogin: ApiLogin = ApiLogin.instance) {

    fun fetchLogin() : Flow<LoginResponse> = flow {

            emit(apiLogin.login())

    }.flowOn(Dispatchers.IO)


    companion object {
        val instance by lazy { LoginRepository() }
    }
}