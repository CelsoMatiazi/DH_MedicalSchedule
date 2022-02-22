package com.matiazicelso.medicalschedule.data.repository

import com.matiazicelso.medicalschedule.data.model.ProfileResponse
import com.matiazicelso.medicalschedule.data.netWork.Api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class UserRepository(private val api: Api = Api.instance) {
    fun fetchProfile() : Flow<ProfileResponse> = flow {
        while(true){
            emit(api.getProfile())
            kotlinx.coroutines.delay(5000)
        }
    }.flowOn(Dispatchers.IO)


    companion object {
        val instance by lazy { UserRepository() }
    }
}